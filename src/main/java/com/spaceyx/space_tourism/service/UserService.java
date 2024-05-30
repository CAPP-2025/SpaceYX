package com.spaceyx.space_tourism.service;

import com.spaceyx.space_tourism.model.UserEntity;
import com.spaceyx.space_tourism.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
