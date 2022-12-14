package com.demo.api.controller;

import com.demo.api.model.User;
import com.demo.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    // 회원가입 Create
    @PostMapping("")
    private void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }

    // 로그인
    
    // 프로파일 정보 가져오기 Read
    @GetMapping("/{loginId}")
    private void getProfiles(@PathVariable String loginId){
        userService.getProfiles(loginId);
    }


    // 회원 정보 수정 Update
    @PutMapping("")
    private void updateUserData(@RequestBody User user,@PathVariable String loginId) {
        userService.updateUserData(user);
    }

    // 회원 탈퇴 Delete (Soft Delete)
    @DeleteMapping("/{loginId}")
    private void deleteUser(@PathVariable String loginId){
        userService.deleteUser(loginId);
    }

}
