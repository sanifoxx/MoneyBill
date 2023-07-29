package com.moneybill.moneybill.service.user;

import com.moneybill.moneybill.dto.UserCreateDto;
import com.moneybill.moneybill.dto.UserInfoDto;

public interface UserService {

    void createUser(UserCreateDto userCreateDto);

    UserInfoDto getUserById(Long userId, Long requestingUserId);
}
