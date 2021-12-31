package com.fpt.controller.staff;

import com.fpt.dto.response.CustomerDTO;
import com.fpt.entity.Customer;
import com.fpt.repo.CustomerRepo;
import com.fpt.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/staff/customer")
@Slf4j
public class CustomerController {
    final CustomerService customerService;

    final CustomerRepo customerRepo;

    public CustomerController(CustomerService customerService, CustomerRepo customerRepo) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> findAll(@RequestParam("n") Optional<Integer> n, @RequestParam("s") Optional<Integer> s, @RequestParam("p") Optional<String> p, @RequestParam("d") Optional<Integer> d) {
        int number = n.orElse(0);
        int size = s.orElse(10);
        String prop = p.orElse("name");
        int direction = d.orElse(0);

        Pageable pageable = PageRequest.of(number, size, Sort.by(direction == 0 ? Sort.Direction.ASC : Sort.Direction.DESC, prop));
        try {
            Page<Customer> customers = customerService.findAll(pageable);
            if (customers.getTotalElements() > 0) {
                return new ResponseEntity<Page<Customer>>(customers, HttpStatus.OK);
            }
            return new ResponseEntity<Page<Customer>>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("error: " + e);
            return new ResponseEntity<Page<Customer>>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable("id") Integer id) {
        if (id != null) {
            try {
                CustomerDTO customerDTO = customerService.findById(id);
                if (customerDTO != null) {
                    return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
                }
                return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
            } catch (Exception e) {
                log.error("error:" + e);
                return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
            }

        }
        return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
    }

    public String gencode() {
        String code = customerRepo.findLastCode();
        Integer id = customerRepo.findLastId();

        String strId = String.valueOf(id + 1);

        StringBuilder builder = new StringBuilder();
        builder.append("KHSGES");
        builder.append("0".repeat(Math.max(0, 8 - strId.length())));
        builder.append(strId);
        return builder.toString();
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = new CustomerDTO();
        customer.setCode(gencode());
        return null;
    }


}
