package com.example.md_back.controller;

import com.example.md_back.model.Word;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/word")
    public String wordHome(Model model) {
        model.addAttribute("words", wordService.getWords());
        return "word/home";
    }

    @GetMapping("/word/insert")
    public String insertForm() {
        return "word/insertForm";
    }

    @GetMapping("/word/{wordId}/update")
    public String updateForm(Model model, @PathVariable int wordId) {
        model.addAttribute("word", wordService.findById(wordId));
        return "word/updateForm";
    }

    @GetMapping("/word/{wordId}")
    public String wordDetail(Model model, @PathVariable int wordId) {
        Word word = wordService.findById(wordId);
        model.addAttribute("word", wordService.findById(wordId));
        return "word/detail";
    }
}