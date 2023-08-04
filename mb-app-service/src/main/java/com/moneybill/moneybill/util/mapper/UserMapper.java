package com.moneybill.moneybill.util.mapper;

import com.moneybill.moneybill.dto.user.UserInfoDto;
import com.moneybill.moneybill.model.User;

public final class UserMapper {

    public static UserInfoDto toInfoDto(User user) {
        return UserInfoDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
