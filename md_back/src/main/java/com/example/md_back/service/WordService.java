package com.example.md_back.service;

import com.example.md_back.dto.RequestNamesDto;
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
    public void insertWord(RequestNamesDto requestNamesDto, User user) {
        Word word = Word.builder()
                .shortName(requestNamesDto.getShortName())
                .engName(requestNamesDto.getEngName())
                .korName(requestNamesDto.getKorName())
                .banWord(requestNamesDto.isBanWord())
                // meaning
                .creationUser(user)
                .deleteStatus(false)
                .build();
        wordRepository.save(word);
    }

    @Transactional
    public void updateWord(int wordId, RequestNamesDto requestNamesDto, User user) {
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("단어 수정 실패 : 단어를 찾을 수 없습니다.");
                });
        word.setShortName(requestNamesDto.getShortName());
        word.setEngName(requestNamesDto.getEngName());
        word.setKorName(requestNamesDto.getKorName());
        word.setBanWord(requestNamesDto.isBanWord());
        // meaning
        word.setModifyUser(user);
        wordRepository.save(word);
    }

    @Transactional
    public void deleteWord(int wordId, User user) {
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("단어 삭제 실패 : 단어를 찾을 수 없습니다.");
                });
        word.setDeleteStatus(true);
        word.setModifyUser(user);
        wordRepository.save(word);
    }

    @Transactional
    public void deleteWordDB(int wordId) {
        wordRepository.deleteById(wordId);
    }

    @Transactional(readOnly = true)
    public Word findById(int wordId) {
        return wordRepository.findById(wordId).orElseThrow(() -> {
            return new IllegalArgumentException("단어 찾기 실패 : " + wordId);
        });
    }

    @Transactional
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

    @Transactional
    public Word wordDetail(int wordId) {
        return wordRepository.findById(wordId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("단어 조회 실패 : 단어를 찾을 수 없습니다.");
                });
    }
}
