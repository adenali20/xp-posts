package com.xp.xpposts.controller;

import com.xp.xpposts.model.SignupDto;
import com.xp.xpposts.model.User;
import com.xp.xpposts.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/expensesrv")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/user/register")
    public User addPost(@RequestBody SignupDto signupDto){
        User user = new User();
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        if(userService.findUserByUserName(signupDto.getUsername()) != null){
            return user;
        }
        userService.saveUser(user);
        return userService.findUserByUserName(signupDto.getUsername());
    }
    @GetMapping("/user/get")
    public String get(){
      return "successfull from deply-2";
    }

}
