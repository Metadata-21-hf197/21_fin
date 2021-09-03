package com.example.md_back.controller.api;

import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class WordApiController {

    @Autowired
    private WordService wordService;

    @PostMapping("/word/insert")
    public int insert(@RequestBody RequestNamesDto requestNamesDto) { // 세션의 유저 정보 받아 옴
        wordService.insertWord(requestNamesDto, null); // user: principal.getUser()
        // httpStatus return
        return HttpStatus.OK.value();
    }

    @PutMapping("/word/{wordId}/update")
    public int update(@PathVariable int wordId, @RequestBody RequestNamesDto requestNamesDto) { // 세션의 유저 정보 받아 옴
        wordService.updateWord(wordId, requestNamesDto, null); // user: principal.getUser()
        // httpStatus return
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/word/{wordId}/delete")
    public int delete(@PathVariable int wordId) {
        wordService.deleteWord(wordId, null); // user: principal.getUser()
        // httpStatus return
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/word/{wordId}/deleteDB")
    public void deleteDB(@PathVariable int wordId){
        wordService.deleteWordDB(wordId);
    }
}
