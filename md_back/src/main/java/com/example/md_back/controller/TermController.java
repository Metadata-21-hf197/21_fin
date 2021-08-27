package com.example.md_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TermController {

    @GetMapping("/term/insertForm")
    public String insertForm() {
        return "term/insertForm";
    }

    @GetMapping("/term/updateForm")
    public String updateForm() {
        return "term/updateForm";
    }

    @GetMapping("/term/{termId}")
    public String termDetail(){
        return "term/detail";
    }

    @GetMapping("/term")
    public String termHome() {
        return "term/home";
    }
}
