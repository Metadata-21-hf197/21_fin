package com.example.md_back.user;

import com.example.md_back.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogInOutController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/user/login")
    public String loginForm(@ModelAttribute("login") LoginDTO login, HttpServletRequest request) {
        return "user/login";
    }

}
