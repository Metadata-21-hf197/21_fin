package com.example.md_back.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        System.out.println("login Success in");
        System.out.println("success : " + authentication.getName());
        //return에 맞게 수정 필요
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String data = " { \"response\" : {"+
                " \"error\" : false , "+
                " \"message\" : \"로그인하였습니다.\"} } ";
        PrintWriter out = response.getWriter();
        out.print(data);
        out.flush();
        out.close();

    }
}
