package com.example.md_back.user;

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

    public User getUser(String memberName) { return userMapper.getUser(memberName);}

    public User checkUser(User user) { return userMapper.checkUser(user);}

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userMapper.getUser(id);
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(user != null) {
            authorities.add(new SimpleGrantedAuthority(user.getUserRole()));
            user.setAuthorities(user.getAuthorities());
        }
        return user;
    }
}
