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

@Controller
public class TermController {

    @Autowired
    private TermService termService;

    @GetMapping("/table/term/insert")
    public String insertForm() {
        return "term/insertForm";
    }

    @GetMapping("/table/term/{termId}/update")
    public String updateForm(Model model, @PathVariable int termId) {
        model.addAttribute("term", termService.findById(termId));
        return "term/updateForm";
    }

    @GetMapping("/table/term/{termId}")
    public String termDetail(Model model, @PathVariable int termId){
        model.addAttribute("term", termService.findById(termId));
        return "term/detail";
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
