package com.example.md_back.controller;

import com.example.md_back.service.TermService;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TermController {

    @Autowired
    private TermService termService;

    @Autowired
    private WordService wordService;

    @GetMapping("/table/term/insert")
    public String insertForm() {
        return "term/insertForm";
    }

    @GetMapping("/table/term/{termId}/update")
    public Map<String, Object> updateForm(@PathVariable int termId) {
        Map<String, Object> map = new HashMap<>();
        map.put("url", "term/updateForm");
        map.put("term", termService.findById(termId));
        return map;
    }

    @GetMapping("/table/term/{termId}")
    public Map<String, Object> termDetail(@PathVariable int termId){
        Map<String, Object> map = new HashMap<>();
        map.put("url", "term/detail");
        map.put("term", termService.findById(termId));
        map.put("twords", termService.getWordListByTermId(termId));
        map.put("words", wordService.getWords());
        return map;
    }

    @GetMapping("/table/term")
    public Map<String, Object> termHome() {
        Map<String, Object> map = new HashMap<>();
        map.put("termList", termService.getTerms());
        return map;
    }
}
