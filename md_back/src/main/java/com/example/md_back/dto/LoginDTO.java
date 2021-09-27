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
    private int memberId;
    private String memberName;
    private String userRole;

    private String password;
//

    private boolean isEnabled = true;
    private String username;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonExpired  = true;
    private boolean isAccountNonLocked  = true;

    private Collection<? extends GrantedAuthority> authorities;
}
