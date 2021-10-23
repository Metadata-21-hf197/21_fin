package com.example.md_back.service;

import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.model.Term;
import com.example.md_back.model.User;
import com.example.md_back.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TermService {

    @Autowired
    private TermRepository termRepository;

    @Transactional
    public void insertTerm(RequestNamesDto requestNamesDto, User user) {
        Term term = Term.builder()
                .shortName(requestNamesDto.getShortName())
                .engName(requestNamesDto.getEngName())
                .korName(requestNamesDto.getKorName())
                .banWord(requestNamesDto.isBanWord())
                .meaning(requestNamesDto.getMeaning())
                .creationUser(user)
                .deleteStatus(false)
                // word - term relation
                .build();
        termRepository.save(term);
    }

    @Transactional
    public void updateTerm(int termId, RequestNamesDto requestNamesDto, User user) {
        Term term = termRepository.findById(termId)
                .orElseThrow(() -> new IllegalArgumentException("용어 수정 실패 : 용어를 찾을 수 없습니다."));
        term.setShortName(requestNamesDto.getShortName());
        term.setEngName(requestNamesDto.getEngName());
        term.setKorName(requestNamesDto.getKorName());
        // word - term relation
        term.setMeaning(requestNamesDto.getMeaning());
        term.setBanWord(requestNamesDto.isBanWord());
        term.setModifyUser(user);
        termRepository.save(term);
    }

    @Transactional
    public void deleteTerm(int termId, User user) {
        Term term = termRepository.findById(termId)
                .orElseThrow(() -> new IllegalArgumentException("용어 삭제 실패 : 용어를 찾을 수 없습니다."));
        term.setDeleteStatus(true);
        term.setModifyUser(user);
        termRepository.save(term);
    }

    @Transactional
    public void deleteTermDB(int termId) {
        termRepository.deleteById(termId);
    }

    @Transactional(readOnly = true)
    public Term findById(int termId) {
        return termRepository.findById(termId).orElseThrow(() -> new IllegalArgumentException("용어 찾기 실패 : " + termId));
    }

    @Transactional(readOnly = true)
    public List<Term> findByShortName(String shortName) {
        return termRepository.findByShortName(shortName);
    }

    @Transactional(readOnly = true)
    public List<Term> findByEngName(String engName) {
        return termRepository.findByEngName(engName);
    }

    @Transactional(readOnly = true)
    public List<Term> findByKorName(String korName) {
        return termRepository.findByKorName(korName);
    }

    @Transactional(readOnly = true)
    public List<Term> findByName(String name) { return termRepository.findByName(name); }

    @Transactional
    public List<Term> getTerms() {
        return termRepository.getTrueTerms();
    }
}
