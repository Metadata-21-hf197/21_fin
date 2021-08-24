package com.example.md_back.controller.api;

import com.example.md_back.model.Word;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class WordApiController {

    @Autowired
    private WordService wordService;

    @PostMapping("/word/insert")
    public Map<String, Object> insert(@RequestBody Word word) { // 세션의 유저 정보 받아 옴
        wordService.insert(word, null); // user: principal.getUser()
        // httpStatus return
        return null;
    }

    @PutMapping("/word/{wordId}/update")
    public Map<String, Object> update(@PathVariable int wordId, @RequestBody Word word) { // 세션의 유저 정보 받아 옴
        wordService.update(wordId, word, null); // user: principal.getUser()
        // httpStatus return
        return null;
    }

    @DeleteMapping("/word/{wordId}/delete")
    public Map<String, Object> delete(@PathVariable int wordId) {
        wordService.delete(wordId, null); // user: principal.getUser()
        // httpStatus return
        return null;
    }

    @DeleteMapping("/word/{wordId}/deleteDB")
    public void deleteDB(@PathVariable int wordId){
        wordService.deleteDB(wordId);
    }
}
