package com.example.md_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DomainController {

    @GetMapping("/domain")
    public String domainHome() { return "domain/home"; }

    @GetMapping("/domain/insert")
    public String insertForm(){
        return "domain/insertForm";
    }

    @GetMapping("/domain/update")
    public String updateForm(){
        return "domain/updateForm";
    }

    @GetMapping("/domain/{domainId}")
    public String domainDetail()  {return "domain/detail";}
}
