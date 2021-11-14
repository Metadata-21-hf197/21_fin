package com.example.md_back.controller;

import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/table/word")
    public Map<String, Object> wordHome(Model model) {
        Map<String, Object> res = new HashMap<>();
        res.put("words", wordService.getWords());
        System.out.println(wordService.getWords());
        return res;
    }

    @GetMapping("/table/word/insert")
    public String insertForm() {
        return "word/insertForm";
    }

    @GetMapping("/table/word/{wordId}/update")
    public Map<String, Object> updateForm(@PathVariable int wordId) {
        Map<String, Object> res = new HashMap<>();
        res.put("url", "word/updateForm");
        res.put("word", wordService.findById(wordId));
        return res;
    }

    @GetMapping("/table/word/{wordId}")
    public Map<String, Object> wordDetail(@PathVariable int wordId) {
        Map<String, Object> res = new HashMap<>();
        res.put("url", "word/detail");
        res.put("word", wordService.findById(wordId));
        return res;
    }
}