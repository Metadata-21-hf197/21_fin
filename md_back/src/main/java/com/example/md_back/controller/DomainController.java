package com.example.md_back.controller;

import com.example.md_back.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DomainController {

    @Autowired
    private DomainService domainService;

    @GetMapping("/table/domain")
    public String domainHome(Model model) {
        model.addAttribute("domains", domainService.getDomains());
        return "table/domain";
    }

    @GetMapping("/table/domain/insert")
    public String insertForm(){
        return "domain/insertForm";
    }

    @GetMapping("/table/domain/{domainId}/update")
    public String updateForm(Model model, @PathVariable int domainId){
        model.addAttribute("domain",domainService.findById(domainId));
        return "domain/updateForm";
    }

    @GetMapping("/table/domain/{domainId}")
    public String domainDetail(Model model, @PathVariable int domainId)  {
        model.addAttribute("domain", domainService.findById(domainId));
        return "domain/detail";
    }
}