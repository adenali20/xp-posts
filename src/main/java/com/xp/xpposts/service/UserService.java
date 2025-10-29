package com.xp.xpposts.service;

import com.xp.xpposts.model.User;
import com.xp.xpposts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public void saveUser(User user) throws UsernameNotFoundException {
       userRepository.save(user);
    }

    public User findUserByUserName(String userName) throws UsernameNotFoundException {
        return userRepository.findByUsername(userName);
    }
}
