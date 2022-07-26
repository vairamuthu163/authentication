package com.example.authentication.repository;

import com.example.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Integer> {
    User findByEmailAndPassword(String email,String password);
    User findByEmail(String email);
}
