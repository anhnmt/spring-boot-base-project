package com.example.baseproject.common.mappers;

import com.example.baseproject.domains.model.User;
import com.example.baseproject.domains.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public User convertToUser(Long userId, UserRequest userRequest) {
        return User.builder()
                .userId(userId)
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

    public User convertToUser(User user, UserRequest userRequest) {
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return user;
    }

    public User convertToUser(UserRequest userRequest) {
        log.info(String.valueOf(userRequest));
        return modelMapper.map(userRequest, User.class);
    }
}
