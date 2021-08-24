package com.example.md_back.service;

import com.example.md_back.model.User;
import com.example.md_back.model.Word;
import com.example.md_back.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    @Transactional
    public void insert(Word requestWord, User user){
        Word word = requestWord;
        word.setDeleteStatus(false);
        word.setCreationUser(user);
        word.setModifyUser(user);
        wordRepository.save(word);
    }

    @Transactional
    public void update(int wordId, Word requestWord, User user){
        Word word = wordRepository.findById(wordId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("단어 수정 실패 : 단어를 찾을 수 없습니다.");
                });
        // word.setFields(requestWord.getFields);
    }

    @Transactional
    public void delete(int wordId, User user){
        Word word = wordRepository.findById(wordId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("단어 삭제 실패 : 단어를 찾을 수 없습니다.");
                });
        word.setDeleteStatus(true);
        word.setModifyUser(user);
        //word.setModifyDate( getTimestamp );
    }

    @Transactional
    public void deleteDB(int wordId){
        wordRepository.deleteById(wordId);
    }

    @Transactional(readOnly = true)
    public Word findById(int wordId){
        return wordRepository.findById(wordId).orElseThrow(()->{
           return new IllegalArgumentException("단어 찾기 실패 : wordId=" + wordId);
        });
    }

    @Transactional(readOnly = true)
    public List<Word> findByEngName(String engName){
        return wordRepository.findByEngName(engName);
    }

    @Transactional(readOnly = true)
    public List<Word> findByKorName(String korName){
        return wordRepository.findByKorName(korName);
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
