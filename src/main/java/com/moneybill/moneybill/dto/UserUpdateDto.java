package com.moneybill.moneybill.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserUpdateDto {

    @NotBlank
    @Size(max = 64)
    private String username;

    @Email
    @Size(max = 128)
    private String email;

    @Size(max = 64)
    private String firstName;

    @Size(max = 64)
    private String lastName;
}
