package com.example.authentication.service;

import com.example.authentication.entity.User;

import java.util.Optional;

public interface AuthService {
    User findByEmailAndPassword(String email,String password);
    User save(User student);
    User findByEmail(String email);
}
