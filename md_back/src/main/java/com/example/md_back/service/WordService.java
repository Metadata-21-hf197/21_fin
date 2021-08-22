package com.example.md_back.service;

import com.example.md_back.model.Word;
import com.example.md_back.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    /** 유저 정보도 받아서 단어의 작성자에 추가해야함
     * @param requestWord, user
     */
    @Transactional
    public void insertWord(Word requestWord){
        // word.setFields();
        wordRepository.save(requestWord);
    }

    /** 세션의 유저정보 받아서 단어의 수정자에 추가해야함
     * @param wordId, requestWord, user
     */
    @Transactional
    public void updateWord(int wordId, Word requestWord){
        Word word = wordRepository.findById(wordId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("단어 수정 실패 : 단어를 찾을 수 없습니다.");
                });
        // word.setFields(requestWord.getFields);
    }

    @Transactional
    public void deleteWord(int wordId){
        wordRepository.deleteById(wordId);
    }

    /**
     * 단어 상세 보기
     * @param wordId
     * @return
     */
    @Transactional
    public Word wordDetail(int wordId){
        return wordRepository.findById(wordId)
                .orElseThrow(()->{
            return new IllegalArgumentException("단어 조회 실패 : 단어를 찾을 수 없습니다.");
        });
    }
}
