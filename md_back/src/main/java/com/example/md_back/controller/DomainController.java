package com.example.md_back.controller;

import com.example.md_back.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DomainController {

    @Autowired
    private DomainService domainService;

    @GetMapping("/table/domain")
    public Map<String, Object> domainHome() {
        Map<String, Object> map = new HashMap<>();
        map.put("domainList", domainService.getDomains());
        return map;
    }

    @GetMapping("/table/domain/insert")
    public String insertForm() {
        return "domain/insertForm";
    }

    @GetMapping("/table/domain/{domainId}/update")
    public Map<String, Object> updateForm(@PathVariable int domainId) {
        Map<String, Object> map = new HashMap<>();
        map.put("url", "domain/updateForm");
        map.put("domain", domainService.findById(domainId));
        return map;
    }

    @GetMapping("/table/domain/{domainId}")
    public Map<String, Object> domainDetail(@PathVariable int domainId) {
        Map<String, Object> map = new HashMap<>();
        map.put("url", "domain/detail");
        map.put("domain", domainService.findById(domainId));
        return map;
    }
}