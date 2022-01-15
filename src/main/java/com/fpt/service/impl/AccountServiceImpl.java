package com.fpt.service.impl;

import com.fpt.dto.AccountDTO;
import com.fpt.dto.CustomUserDetail;
import com.fpt.dto.UserDetail;
import com.fpt.email.EmailServiceImpl;
import com.fpt.entity.Account;
import com.fpt.entity.AuthProvider;
import com.fpt.entity.OTP;
import com.fpt.mapper.AccountMapper;
import com.fpt.repo.AccountRepo;
import com.fpt.repo.OTPRepo;
import com.fpt.security.auth2.OAuth2UserInfo;
import com.fpt.service.AccountService;
import com.fpt.service.FileManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountRepo accountRepo;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AccountMapper accountMapper;

	@Autowired
	OTPRepo otpRepo;

	@Autowired
	EmailServiceImpl emailService;

	@Autowired
	FileManagerServiceImpl fileManagerService;
	
	@Override
	public List<AccountDTO> findAll() {
		return this.accountRepo.findAll().stream()
				.map(entity -> this.accountMapper.cvToDTO(entity))
				.collect(Collectors.toList());
	}

	@Override
	public AccountDTO findById(Integer id) {
		return this.accountMapper.cvToDTO(this.accountRepo.findById(id).get());
	}

	@Override
	public AccountDTO create(AccountDTO dto) {
		return this.accountMapper.cvToDTO(this.accountRepo.save(this.accountMapper.cvToEntity(dto)));
	}

	@Override
	public AccountDTO update(AccountDTO dto) {
		return this.accountMapper.cvToDTO(this.accountRepo.save(this.accountMapper.cvToEntity(dto)));
	}

	@Override
	public AccountDTO delete(Integer id) {
		Account account = this.accountRepo.findById(id).get();
		this.accountRepo.delete(account);
		return this.accountMapper.cvToDTO(account);
	}

	@Override
	public boolean isExist(String username) {
		Account account = accountRepo.findByUsername(username);
		if(account == null) {
			return false;
		}
		return true;
	}

	@Override
	public Account findActiveByUsername(String username) {
		return this.accountRepo.findActiveByUsername(username);
	}

	@Override
	public UserDetail findByUsernameAndPassword(String username, String password) {
		Optional<Account> entity = Optional.ofNullable(accountRepo.findByUsernameAndPassword(username, password));
		List<Integer> roles = entity.get().getAuthorities().stream()
				.map(authority -> authority.getRole().getId())
				.collect(Collectors.toList());
		if(entity.isPresent()) {
			UserDetail userDetail = new UserDetail(entity.get().getId(),entity.get().getUsername(), entity.get().getPassword(),roles);
			return userDetail;
//			return this.accountMapper.cvToDTO(entity.get());
		}
		return null;
	}

	@Override
	public Account registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo) {
		Account account = new Account();
		account.setEmail(oAuth2UserInfo.getEmail());
		account.setFullname(oAuth2UserInfo.getName());
		account.setPhoto(oAuth2UserInfo.getImageUrl());
		account.setStatus(1);
		return accountRepo.save(account);
	}
	@Override
	public Account updateOauth2User(Account account, String provider, OAuth2UserInfo oAuth2UserInfo) {
		account.setEmail(oAuth2UserInfo.getEmail());
		return accountRepo.save(account);
	}

	@Override
	public OTP generateOTP(Account account) {
		OTP otp = new OTP(account);
		return saveOTP(otp);
	}

	@Override
	public OTP saveOTP(OTP otp) {
		return otpRepo.save(otp);
	}

	@Override
	public OTP getOTPByUser(Account account) {
		return otpRepo.findByUser(account).orElse(null);
	}

	@Override
	public void verifyOTP(OTP otp, String otpCode) {
		if (!otp.getCode().equals(otpCode)) {
			throw new RuntimeException("Wrong opt code");
		} else if (otp.isExpired()) {
			throw new RuntimeException("OTP is expired");
		}
	}

	@Override
	public OTP retrieveNewOTP(Account account) {
		OTP otp = getOTPByUser(account);
		if (otp == null) {
			otp = generateOTP(account);
			return otp;
		} else {
			OTP newOTP = new OTP();
			otp.setCode(newOTP.getCode());
			otp.setIssueAt(newOTP.getIssueAt());
			return otp;
		}
	}
	@Override
	public Object sendFogotPasswordMail(String email) {
		Account account = accountRepo.findByEmail(email);
		if (account == null) {
			throw new UsernameNotFoundException("Email không tồn tại trong hệ thống");
		}
		Random random = new Random();

		String password = random.ints(97, 123)
				.limit(6)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		account.setPassword(passwordEncoder.encode(password));
		accountRepo.save(account);
		emailService.sendSimpleMessage(account.getEmail(),
				"Link FogotPassword",
				"Mật khẩu mới của bạn là: " + password);
		return ResponseEntity.ok().body("check token in mail");
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = this.accountRepo.findByUsername(username);
		if(account == null) {
			throw  new UsernameNotFoundException(username);
		}
		return new CustomUserDetail(account);
	}

	@Override
	public Account getUserById(Integer id) {
		try {
			Account account = accountRepo.getUserById(id);
			return account;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void activeAccount(Account account) {
		account.setStatus(1);
	}
}
