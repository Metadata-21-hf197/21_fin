package com.example.md_back.user;

import com.example.md_back.mappers.UserMapper;
import com.example.md_back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUser(String memberName) { return userMapper.getUser(memberName);}
}
