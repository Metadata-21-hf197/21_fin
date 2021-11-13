package com.example.md_back.controller;

import com.example.md_back.service.BanWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BanWordController {

    @Autowired
    private BanWordService banWordService;

    @GetMapping("/table/banword")
    private String home(Model model) {
        model.addAttribute("banWords", banWordService.getBanWordList());
        return "table/banword";
    }

    @GetMapping("/table/banword/insert")
    private String insertForm() {
        return "banword/insertForm";
    }
}
