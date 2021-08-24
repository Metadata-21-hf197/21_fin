package com.example.md_back.controller;

import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/word")
    public String wordHome(){
        return "word/home";
    }

    @GetMapping("/word/insert")
    public String insertForm() {
        return "word/insertForm";
    }

    @GetMapping("/word/{wordId}/update")
    public String updateForm(@PathVariable int wordId) {
        wordService.wordDetail(wordId);
        return "word/updateForm";
    }

    @GetMapping("/word/{wordId}")
    public String wordDetail(@PathVariable int wordId) {
        wordService.wordDetail(wordId);
        return "word/detail";
    }
}
