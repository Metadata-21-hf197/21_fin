package com.example.md_back.controller;

import com.example.md_back.service.BanWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class BanWordController {

    @Autowired
    private BanWordService banWordService;

    @GetMapping("/banword")
    private String home(Model model){
        model.addAttribute("banWords", banWordService.getBanWordList());
        return "banword/home";
    }

    @PostMapping("/banword/valid")
    private String search(Model model, @RequestBody List<String> names){
        model.addAttribute("banWord", banWordService.isValid(names));
        return "/banword";
    }

    @GetMapping("/banword/insert")
    private String insertForm(){
        return "banword/insertForm";
    }
}
