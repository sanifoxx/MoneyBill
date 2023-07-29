package com.moneybill.moneybill.service.user;

import com.moneybill.moneybill.dto.UserCreateDto;
import com.moneybill.moneybill.dto.UserInfoDto;
import com.moneybill.moneybill.exception.access_denied.AccessDeniedException;
import com.moneybill.moneybill.exception.already_exists.UserAlreadyExistsException;
import com.moneybill.moneybill.exception.not_found.UserNotFoundException;
import com.moneybill.moneybill.model.User;
import com.moneybill.moneybill.repository.user.UserRepository;
import com.moneybill.moneybill.util.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
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

    @Transactional(readOnly = true)
    @Override
    public UserInfoDto getUserById(Long userId, Long requestingUserId) {
        if (!userId.equals(requestingUserId)) {
            throw new AccessDeniedException("Access denied. Not enough rights");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found")
                );
        return UserMapper.toInfoDto(user);
    }
}
