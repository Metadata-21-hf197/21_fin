package com.example.md_back.controller.api;

import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.service.WordService;
import com.example.md_back.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class WordApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private WordService wordService;

    @PostMapping("/word/insert")
    public Map<String, Object> insert(@RequestBody RequestNamesDto requestNamesDto) { // 세션의 유저 정보 받아 옴
        wordService.insertWord(requestNamesDto, null); // user: principal.getUser()
        // httpStatus return
        return null;
    }

    @PutMapping("/word/{wordId}/update")
    public Map<String, Object> update(@PathVariable int wordId, @RequestBody RequestNamesDto requestNamesDto) { // 세션의 유저 정보 받아 옴
        wordService.updateWord(wordId, requestNamesDto, null); // user: principal.getUser()
        // httpStatus return
        return null;
    }

    @DeleteMapping("/word/{wordId}/delete")
    public Map<String, Object> delete(@PathVariable int wordId) {
        wordService.deleteWord(wordId, null); // user: principal.getUser()
        // httpStatus return
        return null;
    }

    @DeleteMapping("/word/{wordId}/deleteDB")
    public void deleteDB(@PathVariable int wordId){
        wordService.deleteWordDB(wordId);
    }
}
