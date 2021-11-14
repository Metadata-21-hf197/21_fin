package com.example.md_back.controller;

import com.example.md_back.model.BanWord;
import com.example.md_back.service.BanWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BanWordController {

    @Autowired
    private BanWordService banWordService;

    @GetMapping("/table/banword")
    private String home(Model model) {
        List<String> banWordList = new ArrayList<>();
        banWordList = banWordService.getBanWordList();

        Map<String, Object> map = new HashMap<>();
        map.put("banWordList", banWordList);
        return "table/banword";
    }

    @GetMapping("/table/banword/insert")
    private String insertForm() {
        return "banword/insertForm";
    }
}
