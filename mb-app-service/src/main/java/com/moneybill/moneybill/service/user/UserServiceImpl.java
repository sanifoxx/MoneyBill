package com.moneybill.moneybill.service.user;

import com.moneybill.moneybill.dto.user.UserCreateDto;
import com.moneybill.moneybill.dto.user.UserInfoDto;
import com.moneybill.moneybill.dto.user.UserUpdateDto;
import com.moneybill.moneybill.exception.already_exists.AlreadyExistsException;
import com.moneybill.moneybill.exception.already_exists.UserAlreadyExistsException;
import com.moneybill.moneybill.exception.not_found.UserNotFoundException;
import com.moneybill.moneybill.model.User;
import com.moneybill.moneybill.repository.user.UserRepository;
import com.moneybill.moneybill.util.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserInfoDto createUser(final UserCreateDto userCreateDto) {
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
        return UserMapper.toInfoDto(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    @Override
    public UserInfoDto getUserById(Long userId) {
        User user = getUserByIdOrElseThrow(userId);
        return UserMapper.toInfoDto(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByIdOrElseThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found")
                );
    }

    @Transactional
    @Override
    public UserInfoDto updateUserById(Long userId, UserUpdateDto userUpdateDto) {
        User user = getUserByIdOrElseThrow(userId);
        if (userUpdateDto.getUsername() != null) {
            if (userRepository.existsByUsernameAndIdNot(userUpdateDto.getUsername(), userId)) {
                throw new AlreadyExistsException("Username already exists");
            }
            user.setUsername(userUpdateDto.getUsername());
        }
        if (userUpdateDto.getEmail() != null) {
            if (userRepository.existsByEmailAndIdNot(userUpdateDto.getEmail(), userId)) {
                throw new AlreadyExistsException("Email already exists");
            }
            user.setEmail(userUpdateDto.getEmail());
        }
        if (userUpdateDto.getFirstName() != null) {
            user.setFirstName(userUpdateDto.getFirstName());
        }
        if (userUpdateDto.getLastName() != null) {
            user.setLastName(userUpdateDto.getLastName());
        }
        return UserMapper.toInfoDto(userRepository.save(user));
    }

    @Transactional
    @Override
    public UserInfoDto deleteUserById(Long userId) {
        User user = getUserByIdOrElseThrow(userId);
        userRepository.delete(user);
        return UserMapper.toInfoDto(user);
    }
}
