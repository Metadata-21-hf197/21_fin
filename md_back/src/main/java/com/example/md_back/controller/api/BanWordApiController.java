package com.example.md_back.controller.api;

import com.example.md_back.service.BanWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BanWordApiController {

    @Autowired
    private BanWordService banWordService;

    @PostMapping("/banword")
    public int insert(String name){
        banWordService.insert(name);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/banword")
    public int delete(String name){
        banWordService.delete(name);
        return HttpStatus.OK.value();
    }

    @PutMapping("/valid")
    public boolean isValid(@RequestBody List<String> names){
        return banWordService.isValid(names);
    }
}
