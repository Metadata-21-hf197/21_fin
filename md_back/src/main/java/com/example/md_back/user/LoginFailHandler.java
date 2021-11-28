package com.example.md_back.user;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String msg = exception.getMessage();
        System.out.println("login Failed");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String data = " { \"response\" : {"+
                " \"error\" : true , "+
                " \"message\" : \""+exception.getMessage()+"\"} } ";
        PrintWriter out = response.getWriter();
        out.print(data);
        out.flush();
        out.close();
    }

}
