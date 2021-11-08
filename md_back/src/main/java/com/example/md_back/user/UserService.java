package com.example.md_back.user;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.mappers.UserMapper;
import com.example.md_back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImpl{
    @Autowired
    private UserMapper userMapper;


    public LoginDTO getUser(String memberName) { return userMapper.getUser(memberName);}

    public User checkUser(User user) { return userMapper.checkUser(user);}

    public void insertUser(User user) {userMapper.insertUser(user);}
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        LoginDTO principal = userMapper.getUser(id);
        System.out.println("Service - id : " + id);
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(principal != null) {
            authorities.add(new SimpleGrantedAuthority(principal.getUserRole()));
            principal.setAuthorities(principal.getAuthorities());
        }
        return principal;
    }

}
