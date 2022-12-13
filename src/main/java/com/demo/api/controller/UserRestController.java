package com.demo.api.controller;

import com.demo.api.model.UserDto;
import com.demo.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("")
    private void insertUser(@RequestBody UserDto userDto){
        userService.insertUser(userDto);
    }

//    @GetMapping("")
//    private void listUser(){
//        userService.listUser();
//    }

}
