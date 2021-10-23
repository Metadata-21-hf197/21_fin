package com.example.md_back.controller;

import com.example.md_back.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TermController {

    @Autowired
    private TermService termService;

    @GetMapping("/term/insertForm")
    public String insertForm() {
        return "term/insertForm";
    }

    @GetMapping("/term/{termId}/updateForm")
    public String updateForm(Model model, @PathVariable int termId) {
        model.addAttribute("term", termService.findById(termId));
        return "term/updateForm";
    }

    @GetMapping("/term/{termId}")
    public String termDetail(Model model, @PathVariable int termId){
        model.addAttribute("term", termService.findById(termId));
        return "term/detail";
    }

    @GetMapping("/term")
    public String termHome(Model model) {
        model.addAttribute("terms", termService.getTerms());
        return "term/home";
    }
}
