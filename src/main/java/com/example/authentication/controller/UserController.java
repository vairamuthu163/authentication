package com.example.authentication.controller;


import com.example.authentication.entity.User;
import com.example.authentication.service.AuthService;
import dto.LoginDto;
import dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public String login(@RequestBody UserDto loginDto){
        User user = authService.findByEmail(loginDto.getEmail());
        if(user==null){
           return "Invalid Email and Password!";
        }
        if(user.getIsMerchant()==0) {
         if (!user.getEmail().equals(loginDto.getEmail())) {
                return "Invalid Email";
            } else if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                return "Invalid Password";
            } else {
                return user.getEmail();
            }
        }
        return "not a customer";
    }
    @PostMapping("/signup")
    public String save(@RequestBody User userDto){
        User checkUser = authService.findByEmail(userDto.getEmail());

        if(checkUser!=null){
            return ("user is present!!");
        }else{

            String encoded = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encoded);
            authService.save(userDto);
            return ("New User Created successfully!");
        }
    }


}
