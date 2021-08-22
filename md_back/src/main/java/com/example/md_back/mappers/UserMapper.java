package com.example.md_back.mappers;

import com.example.md_back.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {
    public User getUser(String memberName);
    public User checkUser(User user);
}
