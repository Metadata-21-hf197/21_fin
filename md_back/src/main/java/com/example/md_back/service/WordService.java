package com.example.md_back.service;

import com.example.md_back.model.Word;
import com.example.md_back.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    /** 유저 정보도 받아서 단어의 작성자에 추가해야함
     * @param word, user
     */
    public void insertWord(Word word){
        // word.setFields();
        wordRepository.save(word);
    }

    /** 세션의 유저정보 받아서 단어의 수정자에 추가해야함
     * @param wordId, requestWord, user
     */
    public void updateWord(int wordId, Word requestWord){
        Word word = wordRepository.findById(wordId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("단어 수정 실패 : 단어를 찾을 수 없습니다.");
                });
        // word.setFields(requestWord.getFields);
    }

    public void deleteWord(int wordId){
        wordRepository.deleteById(wordId);
    }
}
