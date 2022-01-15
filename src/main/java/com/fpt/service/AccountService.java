package com.fpt.service;

import com.fpt.dto.AccountDTO;
import com.fpt.dto.UserDetail;
import com.fpt.entity.Account;
import com.fpt.entity.OTP;
import com.fpt.security.auth2.OAuth2UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface AccountService extends UserDetailsService {
	
	public List<AccountDTO> findAll();
	
	public AccountDTO findById(Integer id);
	
	public AccountDTO create(AccountDTO dto);
	
	public AccountDTO update(AccountDTO dto);
	
	public AccountDTO delete(Integer id);
	
	public boolean isExist(String username);
	
	public Account findActiveByUsername(String username);
	
	public UserDetail findByUsernameAndPassword(String username, String password);

	Account registerOauth2User(String provider, OAuth2UserInfo oAuth2UserInfo);

	Account updateOauth2User(Account user, String provider, OAuth2UserInfo oAuth2UserInfo);

	void activeAccount(Account account);

	Account getUserById(Integer id);

	Object sendFogotPasswordMail(String email);
	OTP generateOTP(Account account);
	OTP saveOTP(OTP otp);
	OTP getOTPByUser(Account account);
	void verifyOTP(OTP otp, String otpCode);
	OTP retrieveNewOTP(Account account);

}
