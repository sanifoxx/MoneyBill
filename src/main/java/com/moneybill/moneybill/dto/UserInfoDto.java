package com.moneybill.moneybill.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
}
