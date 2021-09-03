package com.example.md_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TermController {

    @GetMapping("/term/insertForm")
    public String insertForm() {
        return "term/insertForm";
    }

    @GetMapping("/term/{termId}/updateForm")
    public String updateForm(@PathVariable int termId) {
        // response Fields
        return "term/updateForm";
    }

    @GetMapping("/term/{termId}")
    public String termDetail(@PathVariable int termId){
        // termId ~
        return "term/detail";
    }

    @GetMapping("/term")
    public String termHome() {
        return "term/home";
    }
}
