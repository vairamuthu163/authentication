package com.example.authentication.service.impl;

import com.example.authentication.entity.User;
import com.example.authentication.repository.AuthRepository;
import com.example.authentication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    AuthRepository authRepository;

    @Override
    public User findByEmailAndPassword(String email,String password){
        return authRepository.findByEmailAndPassword(email,password);
    }

    @Override
    public User save(User user) {
        return authRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return authRepository.findByEmail(email);
    }

}
