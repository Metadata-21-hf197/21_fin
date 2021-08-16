package com.example.md_back.service;

import com.example.md_back.model.Term;
import com.example.md_back.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TermService {

    @Autowired
    private TermRepository termRepository;

    /** 유저 정보도 받아서 용어의 작성자에 추가해야함
     * @param term, user
     */
    public void insertTerm(Term term){
        // term.setFields();
        termRepository.save(term);
    }

    /** 세션의 유저정보 받아서 용어의 수정자에 추가해야함
     * 용어의 삭제도 이 곳에서 처리
     * @param termId, requestTerm, user
     */
    public void updateTerm(int termId, Term requestTerm){
        Term term = termRepository.findById(termId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("용어 수정 실패 : 용어를 찾을 수 없습니다.");
                });
        // term.setFields(requestTerm.getFields);
    }

    public void deleteTerm(int termId){
        termRepository.deleteById(termId);
    }
}
