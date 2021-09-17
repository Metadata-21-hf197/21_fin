package com.example.md_back.controller.api;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.service.WordService;
import com.example.md_back.user.MyAuthentication;
import com.example.md_back.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class WordApiController {

    @Autowired
    private WordService wordService;

    @Autowired
    private UserService userService;

    @PostMapping("/word")
    public int insert(@RequestBody RequestNamesDto requestNamesDto) { // 세션의 유저 정보 받아 옴
        wordService.insertWord(requestNamesDto, null); // user: principal.getUser()
        // httpStatus return
        return HttpStatus.OK.value();
    }

    @PutMapping("/word/{wordId}")
    public int update(@PathVariable int wordId, @RequestBody RequestNamesDto requestNamesDto) { // 세션의 유저 정보 받아 옴
        wordService.updateWord(wordId, requestNamesDto, null); // user: principal.getUser()
        // httpStatus return
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/word/{wordId}")
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
