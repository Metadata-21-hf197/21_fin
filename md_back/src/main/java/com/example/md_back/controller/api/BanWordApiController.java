package com.example.md_back.controller.api;

import com.example.md_back.service.BanWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BanWordApiController {

    @Autowired
    private BanWordService banWordService;

    @PostMapping("/banword")
    private String insert(String name){
        banWordService.insert(name);
        return "/banword";
    }

    @DeleteMapping("/banword")
    private String delete(String name){
        banWordService.delete(name);
        return "/banword";
    }
}
