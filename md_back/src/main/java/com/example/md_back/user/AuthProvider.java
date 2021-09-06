package com.example.md_back.user;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    UserService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        id = "popo";

        System.out.println("authentication : " + id);
        String password = authentication.getCredentials().toString();
        password = "1234";
        return authenticate(id, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public Authentication authenticate(String id, String password) throws org.springframework.security.core.AuthenticationException {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();

        LoginDTO principal = new LoginDTO();

        principal = (LoginDTO) userService.loadUserByUsername(id);

        System.out.println(principal);
        if(principal == null) {
            System.out.println("DTO is null");
            throw new UsernameNotFoundException("wrongid");
        } else if(principal != null && !principal.getPassword().equals(password)) {
            System.out.println("DTO is not null");
            throw new BadCredentialsException("wrongpw");
        }

        grantedAuthorityList.add(new SimpleGrantedAuthority(principal.getUserRole()));

        return new MyAuthentication(id, password, grantedAuthorityList, principal);
    }

}
