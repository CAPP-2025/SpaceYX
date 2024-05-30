package com.spaceyx.space_tourism.controller;

import com.spaceyx.space_tourism.model.UserEntity;
import com.spaceyx.space_tourism.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
}
