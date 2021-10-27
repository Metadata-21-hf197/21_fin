package com.example.md_back.service;

import com.example.md_back.mappers.TermMapper;
import com.example.md_back.model.Approval;
import com.example.md_back.model.Term;
import com.example.md_back.repository.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TermService {

    @Autowired
    private TermRepository termRepository;
    @Autowired
    private TermMapper termMapper;

    @Transactional
    public void insertTerm(Approval approval) {
        Term term = new Term();
        term.approvalToTerm(approval);
        termRepository.save(term);
    }

    @Transactional
    public void updateTerm(Approval approval) {
        Term term = termRepository.findById(approval.getTargetId())
                .orElseThrow(() -> new IllegalArgumentException("용어 수정 실패 : 용어를 찾을 수 없습니다."));
        term.approvalToTerm(approval);
        termRepository.save(term);
    }

    @Transactional
    public void deleteTerm(Approval approval) {
        Term term = termRepository.findById(approval.getTargetId())
                .orElseThrow(() -> new IllegalArgumentException("용어 삭제 실패 : 용어를 찾을 수 없습니다."));
        term.approvalToTerm(approval);
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
        return termRepository.getTerms();
    }

    public List<Term> getTermListByUserId(int userId) {return termMapper.getTermListByUserId(userId); }
}
