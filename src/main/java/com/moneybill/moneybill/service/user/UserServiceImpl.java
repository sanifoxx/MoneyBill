package com.moneybill.moneybill.service.user;

import com.moneybill.moneybill.dto.UserCreateDto;
import com.moneybill.moneybill.exception.already_exists.UserAlreadyExistsException;
import com.moneybill.moneybill.model.User;
import com.moneybill.moneybill.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(final UserCreateDto userCreateDto) {
        if (userRepository.existsByEmail(userCreateDto.getEmail())) {
            throw new UserAlreadyExistsException("User with email already exists");
        }
        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
            throw new UserAlreadyExistsException("User with username already exists");
        }
        final User user = User.builder()
                .username(userCreateDto.getUsername())
                .email(userCreateDto.getEmail())
                .firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .creationTimestamp(LocalDateTime.now())
                .build();
        userRepository.save(user);
    }
}
