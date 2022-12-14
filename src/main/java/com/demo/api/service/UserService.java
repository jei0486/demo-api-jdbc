package com.demo.api.service;

import com.demo.api.domain.user.UserEntity;
import com.demo.api.domain.user.UserRepository;
import com.demo.api.domain.user.UserState;
import com.demo.api.model.User;
import com.demo.api.util.ModelMapperUtil;
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

    // create
    public void registerUser(User user){
        UserEntity userEntity = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .state(UserState.ACTIVE)
                .name(user.getName())
                .createdAt(LocalDateTime.now())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .build();

        // >> ID 전략 << save 가 아닌 insert 로 한다.
        // save 는 insert or update 지만
        // insert 는 only insert 만 실행한다.
        userRepository.insert(userEntity);
    }

    // read
    public User getProfiles(String loginId){
        UserEntity userEntity = userRepository.findByLoginIdExcludeDeleted(loginId).orElseThrow(()-> new RuntimeException("존재하지 않는 유저입니다."));

        return ModelMapperUtil.map(userEntity,User.class);
    }

    // update
    public void updateUserData(User user){
        UserEntity userEntity = userRepository.findByLoginIdExcludeDeleted(user.getLoginId()).orElseThrow(()-> new RuntimeException("존재하지 않는 유저입니다."));
        boolean result = userRepository.updateUserData(user.getId(),user.getLoginId(),user.getName(),user.getPassword());
    }

    // delete (soft delete)
    public void deleteUser(String loginId) throws RuntimeException {

        UserEntity user = userRepository.findByLoginIdExcludeDeleted(loginId).orElseThrow(()-> new RuntimeException("존재하지 않는 유저입니다."));

        userRepository.deleteById(loginId);
    }

}
