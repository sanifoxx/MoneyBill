package com.moneybill.moneybill.service.user;

import com.moneybill.moneybill.dto.UserCreateDto;
import com.moneybill.moneybill.dto.UserInfoDto;
import com.moneybill.moneybill.dto.UserUpdateDto;

public interface UserService {

    void createUser(UserCreateDto userCreateDto);

    UserInfoDto getUserById(Long userId, Long requestingUserId);

    UserInfoDto updateUserById(Long userId, UserUpdateDto userUpdateDto);
}
