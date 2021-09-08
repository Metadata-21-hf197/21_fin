package com.example.md_back.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserServiceImpl extends UserDetailsService {
    public UserDetails loadUserByUsername(String id);
}
