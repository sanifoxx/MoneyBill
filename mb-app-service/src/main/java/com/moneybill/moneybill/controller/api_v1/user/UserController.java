package com.moneybill.moneybill.controller.api_v1.user;

import com.moneybill.moneybill.dto.user.UserCreateDto;
import com.moneybill.moneybill.dto.user.UserInfoDto;
import com.moneybill.moneybill.dto.user.UserUpdateDto;
import com.moneybill.moneybill.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserInfoDto createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        log.info("POST /users | userCreateDto-Object: {}", userCreateDto);
        return userService.createUser(userCreateDto);
    }

    @GetMapping
    public UserInfoDto getUser(@RequestHeader(name = "X-User-Id") Long userId) {
        log.info("GET /users | X-User-Id={}", userId);
        return userService.getUserById(userId);
    }

    @PutMapping
    public UserInfoDto updateUser(@RequestHeader(name = "X-User-Id") Long userId,
                                  @Valid @RequestBody UserUpdateDto userUpdateDto) {
        log.info("PUT /users | X-User-Id={}, userUpdateDto-Object: {}", userId, userUpdateDto);
        return userService.updateUserById(userId, userUpdateDto);
    }

    @DeleteMapping
    public UserInfoDto deleteUser(@RequestHeader(name = "X-User-Id") Long userId) {
        log.info("DELETE /users | X-User-Id={}", userId);
        return userService.deleteUserById(userId);
    }
}
