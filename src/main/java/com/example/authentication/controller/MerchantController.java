package com.example.authentication.controller;

import com.example.authentication.entity.User;
import com.example.authentication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.security.util.Password;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody User userDto){
        User user = authService.findByEmail(userDto.getEmail());
        if(user==null){
            return "Invalid Email and Password";
        }
        if(user.getIsMerchant()==1) {
            if (!(user.getEmail().equals(userDto.getEmail()))) {
                return "Invalid Email";
            } else if (!(passwordEncoder.matches(userDto.getPassword(), user.getPassword()))) {
                return "Invalid Password";
            } else {
                return user.getEmail();
            }
        }
        return "not a merchant";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User userDto){
        User checkUser= authService.findByEmail(userDto.getEmail());
        if(checkUser!=null){
            return("user is present!!");
        }else{
            String encoded = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encoded);
            userDto.setMerchant(1);
            authService.save(userDto);
            return ("New User Created successfully!");
        }
    }

}
