package com.fpt.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class ChangeProfile {
    private String fullname;
    @Email(message = "Email address invalid!")
    private String email;
    private String photo;
}
