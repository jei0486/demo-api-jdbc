package com.demo.api.service;

import com.demo.api.domain.user.User;
import com.demo.api.domain.user.UserRepository;
import com.demo.api.domain.user.UserState;
import com.demo.api.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void insertUser(UserDto userDto){
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .state(UserState.ACTIVE)
                .name(userDto.getName())
                .createdAt(LocalDateTime.now())
                .loginId(userDto.getLoginId())
                .password(userDto.getPassword())
                .build();

        log.info(String.valueOf(user));
        userRepository.insert(user);
    }

}
