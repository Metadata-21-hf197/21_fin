package com.example.md_back.user;

import com.example.md_back.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class MyAuthentication extends UsernamePasswordAuthenticationToken {
    User user;

    public MyAuthentication(String id, String password, List<GrantedAuthority> grantedAuthorityList, User user) {
        super(id, password, grantedAuthorityList);
        this.user = user;
    }
    public User getUser() {return this.user;}

}
