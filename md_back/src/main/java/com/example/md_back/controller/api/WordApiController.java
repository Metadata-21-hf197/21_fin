package com.example.md_back.controller.api;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.dto.WordDto;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class WordApiController {

    @Autowired
    private WordService wordService;
    // 컨트롤러가 필요한가?

    @PostMapping("/word")
    public int insert(@RequestBody WordDto wordDto, @AuthenticationPrincipal LoginDTO loginDTO) {

        wordService.insertWord(null);
        return HttpStatus.OK.value();
    }

    @PutMapping("/word/{wordId}")
    public int update(@PathVariable int wordId, @RequestBody WordDto wordDto, @AuthenticationPrincipal LoginDTO loginDTO) { // 세션의 유저 정보 받아 옴
        wordService.updateWord(null);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/word/{wordId}")
    public int delete(@PathVariable int wordId, @AuthenticationPrincipal LoginDTO loginDTO) {
        wordService.deleteWord(null);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/word/{wordId}/deleteDB")
    public void deleteDB(@PathVariable int wordId){
        wordService.deleteWordDB(wordId);
    }
}
