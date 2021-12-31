package com.fpt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Integer id;
    private String code;
    private String avatar;
    private String fullname;
    private Integer age;
    private Integer gender;
    private String email;
    private String address;
    private String phone;
    private Integer classId;
}
