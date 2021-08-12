package com.example.md_back.user;


import com.example.md_back.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User getUser(int id);
    public void insertUser(User user);
    public void updateUser(int id, User user);
    public void deleteUser(int id);
    public String login(User user);
}
