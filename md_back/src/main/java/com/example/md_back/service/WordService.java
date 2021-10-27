package com.example.md_back.service;


import com.example.md_back.mappers.WordMapper;
import com.example.md_back.model.Approval;
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

    @Autowired
    private WordMapper wordMapper;

    @Transactional
    public void insertWord(Approval approval) {
        Word word = new Word();
        word.approvalToWord(approval);
        wordRepository.save(word);
    }

    @Transactional
    public void updateWord(Approval approval) {
        Word word = wordRepository.findById(approval.getTargetId())
                .orElseThrow(() -> new IllegalArgumentException("단어 수정 실패 : 단어를 찾을 수 없습니다."));
        word.approvalToWord(approval);
        wordRepository.save(word);
    }

    @Transactional
    public void deleteWord(Approval approval) {
        Word word = wordRepository.findById(approval.getTargetId())
                .orElseThrow(() -> new IllegalArgumentException("단어 삭제 실패 : 단어를 찾을 수 없습니다."));
        word.approvalToWord(approval);
        wordRepository.save(word);
    }

    @Transactional
    public void deleteWordDB(int wordId) {
        wordRepository.deleteById(wordId);
    }

    @Transactional(readOnly = true)
    public Word findById(int wordId) {
        return wordRepository.findById(wordId).orElseThrow(() -> new IllegalArgumentException("단어 찾기 실패 : " + wordId));
    }

    @Transactional(readOnly = true)
    public List<Word> findByShortName(String shortName){
        return wordRepository.findByShortName(shortName);
    }

    @Transactional(readOnly = true)
    public List<Word> findByEngName(String engName) {
        return wordRepository.findByEngName(engName);
    }

    @Transactional(readOnly = true)
    public List<Word> findByKorName(String korName) {
        return wordRepository.findByKorName(korName);
    }

    @Transactional(readOnly = true)
    public List<Word> findByName(String name) {
        return wordRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Word> getWords() {
        return wordRepository.getWords();
    }

    public List<Word> getWordListByUserId(int userId) {return wordMapper.getWordListByUserId(userId); }
}
