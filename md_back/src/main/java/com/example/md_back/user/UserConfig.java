package com.example.md_back.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class UserConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthProvider authProvider;

    //DB를 사용한 사용자 인증 처리 설정
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userService()).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

    }

}
