package com.moneybill.moneybill.service.user;

import com.moneybill.moneybill.dto.user.UserCreateDto;
import com.moneybill.moneybill.dto.user.UserInfoDto;
import com.moneybill.moneybill.dto.user.UserUpdateDto;
import com.moneybill.moneybill.model.User;

public interface UserService {

    UserInfoDto createUser(UserCreateDto userCreateDto);

    UserInfoDto getUserById(Long userId);

    User getUserByIdOrElseThrow(Long userId);

    UserInfoDto updateUserById(Long userId, UserUpdateDto userUpdateDto);

    UserInfoDto deleteUserById(Long userId);
}
