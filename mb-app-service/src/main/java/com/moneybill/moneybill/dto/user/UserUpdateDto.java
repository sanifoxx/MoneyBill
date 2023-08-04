package com.moneybill.moneybill.dto.user;

import com.moneybill.moneybill.annotation.null_or_not_blank.NullOrNotBlank;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserUpdateDto {

    @NullOrNotBlank
    @Size(max = 64)
    private String username;

    @NullOrNotBlank
    @Email
    @Size(max = 128)
    private String email;

    @NullOrNotBlank
    @Size(max = 64)
    private String firstName;

    @NullOrNotBlank
    @Size(max = 64)
    private String lastName;
}
