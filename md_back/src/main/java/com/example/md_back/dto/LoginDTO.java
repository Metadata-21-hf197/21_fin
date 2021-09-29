package com.example.md_back.dto;

import com.example.md_back.model.AccessType;
import com.example.md_back.model.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

public @Data
class LoginDTO implements UserDetails {
    private User user;
    public LoginDTO(User user) {
        this.user = user;
    }
    public LoginDTO() { //security를 위해서 파라미터가 없는 생성자 필요함

    }

    public User getUser() {return user;}
    public int getMemberId() {return user.getMemberId();}
    public String getMemberName() {return user.getMemberName();}
    public String getUserRole() {return user.getUserRole();}
    public String getPassword() {return user.getPassword();}


    private boolean isEnabled = true;
    private String username;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonExpired  = true;
    private boolean isAccountNonLocked  = true;

    private Collection<? extends GrantedAuthority> authorities;
}
