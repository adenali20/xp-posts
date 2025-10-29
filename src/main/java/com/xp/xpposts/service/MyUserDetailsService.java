package com.xp.xpposts.service;

import com.xp.xpposts.model.User;
import com.xp.xpposts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Component
@Profile({"local", "dev"})
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserService  userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
