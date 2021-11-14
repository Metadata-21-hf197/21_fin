package com.example.md_back.controller;

import com.example.md_back.model.Domain;
import com.example.md_back.model.Term;
import com.example.md_back.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DomainController {

    @Autowired
    private DomainService domainService;

    @GetMapping("/table/domain")
    public Map<String, Object> domainHome(Model model) {
        Map<String, Object> map = new HashMap<>();
        List<Domain> domainList = domainService.getDomains();

        map.put("domainList", domainList);
        return map;
    }

    @GetMapping("/table/domain/insert")
    public String insertForm(){
        return "domain/insertForm";
    }

    @GetMapping("/table/domain/{domainId}/update")
    public Map<String, Object> updateForm(Model model, @PathVariable int domainId){
        Domain domain = domainService.findById(domainId);
        Map<String, Object> map = new HashMap<>();
        map.put("url", "domain/updateForm");
        map.put("domain", domain);
        return map;
    }

    @GetMapping("/table/domain/{domainId}")
    public Map<String, Object> domainDetail(Model model, @PathVariable int domainId)  {
        Domain domain = domainService.findById(domainId);
        Map<String, Object> map = new HashMap<>();
        map.put("url", "domain/detail");
        map.put("domain", domain);
        return map;
    }
}