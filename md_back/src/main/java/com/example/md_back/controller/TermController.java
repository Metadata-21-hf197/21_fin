package com.example.md_back.controller;

import com.example.md_back.model.Term;
import com.example.md_back.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TermController {

    @Autowired
    private TermService termService;

    @GetMapping("/table/term/insert")
    public String insertForm() {
        return "term/insertForm";
    }

    @GetMapping("/table/term/{termId}/update")
    public Map<String, Object> updateForm(Model model, @PathVariable int termId) {
        Term term = termService.findById(termId);
        Map<String, Object> map = new HashMap<>();
        map.put("url", "term/updateForm");
        map.put("term", term);
        return map;
    }

    @GetMapping("/table/term/{termId}")
    public Map<String, Object> termDetail(Model model, @PathVariable int termId){
        Term term = termService.findById(termId);
        Map<String, Object> map = new HashMap<>();
        map.put("url", "term/detail");
        map.put("term", term);
        return map;
    }

    @GetMapping("/table/term")
    public Map<String, Object> termHome(Model model) {
        Map<String, Object> map = new HashMap<>();
        List<Term> termList = new ArrayList();

        termList = termService.getTerms();
        map.put("termList", termList);
        return map;
    }
}
