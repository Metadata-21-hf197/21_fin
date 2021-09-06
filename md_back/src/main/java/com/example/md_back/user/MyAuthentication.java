package com.example.md_back.user;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class MyAuthentication extends UsernamePasswordAuthenticationToken {
    LoginDTO principal;

    public MyAuthentication(String id, String password, List<GrantedAuthority> grantedAuthorityList, LoginDTO principal) {
        super(id, password, grantedAuthorityList);
        this.principal = principal;
    }
    public LoginDTO getUser() {return this.principal;}

}
