package com.moneybill.moneybill.controller.user;

import com.moneybill.moneybill.dto.UserCreateDto;
import com.moneybill.moneybill.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        log.info("POST /users | userCreateDto-object: {}", userCreateDto);
        userService.createUser(userCreateDto);
    }
}
