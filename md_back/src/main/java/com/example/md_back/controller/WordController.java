package com.example.md_back.controller;

import com.example.md_back.model.Word;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RequestMapping("/mms")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/word/insert")
    @ResponseBody
    public String wordForm(){
        return "word/form";
    }

    /** 단어 추가 시 권한 확인, 세션의 유저 정보 받아서 서비스로 리턴도 해줘야함
     * @param
     * @return
     */
    @PostMapping("/word/insert") // /word/insert 일 필요가 있는지?
    public Map<String, Object> insert(){ // 세션의 유저 정보 받아 옴
        // word 객체 받아옴
        wordService.insertWord(null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    /** 단어 수정 시 권한 확인, 세션의 유저 정보 받아서 서비스로 리턴도 해줘야함
     * 삭제도 이 곳에서 처리, deleteStatus = True로 update
     * @param wordId, word, principal
     * @return
     */
    @PutMapping("/word/update/{wordId}") // /word/{wordId}로 수정
    public Map<String, Object> update(@PathVariable int wordId){ // 세션의 유저 정보 받아 옴
        // word 객체 받아옴
        wordService.updateWord(wordId,null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    /** 단어 삭제 시 권한 확인
     * DB 에서 단어 삭제 시 사용
     * @param wordId
     * @return
     */
    @DeleteMapping("/word/delete/{wordId}")
    public Map<String, Object> delete(@PathVariable int wordId){
        wordService.deleteWord(wordId);
        // httpStatus return
        return null;
    }
}
