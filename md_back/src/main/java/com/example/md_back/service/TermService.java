package com.example.md_back.service;

import com.example.md_back.dto.TermDto;
import com.example.md_back.mappers.TermMapper;
import com.example.md_back.mappers.TermWordMapper;
import com.example.md_back.mappers.WordMapper;
import com.example.md_back.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TermService {

    @Autowired
    private TermMapper termMapper;

    @Autowired
    private TermWordMapper termWordMapper;

    @Autowired
    private WordMapper wordMapper;

    @Transactional
    public void insertTerm(Approval approval) {
        Term term = new Term();
        term.approvalToTerm(approval);
        termMapper.insertTerm(term);
    }

    @Transactional
    public void insertTermWord(Approval approval) {
        Term term = termMapper.getTermById(approval.getTargetId());
        if (term == null) throw new IllegalArgumentException("용어 단어 추가 실패 : 용어를 찾을 수 없습니다.");
        else if (term.isDeleteStatus()) throw new IllegalArgumentException("용어 단어 추가 실패 : 삭제된 용어입니다.");

        Word word = wordMapper.getWordById(approval.getSlaveId());
        if (word == null) throw new IllegalArgumentException("용어 단어 추가 실패 : 단어를 찾을 수 없습니다.");
        else if (word.isDeleteStatus()) throw new IllegalArgumentException("용어 단어 추가 실패 : 삭제된 단어입니다.");

        termWordMapper.insert(approval.getTargetId(), approval.getSlaveId());
        termMapper.updateTermByTermWord(approval);
    }

    @Transactional
    public void updateTerm(Approval approval) {
        Term term = termMapper.getTermById(approval.getTargetId());
        if (term == null) throw new IllegalArgumentException("용어 수정 실패 : 용어를 찾을 수 없습니다.");
        else if (term.isDeleteStatus()) throw new IllegalArgumentException("용어 수정 실패 : 삭제 된 용어입니다.");

        term.approvalToTerm(approval);
        termMapper.updateTerm(term);
    }

    @Transactional
    public void deleteTerm(Approval approval) {
        Term term = termMapper.getTermById(approval.getTargetId());
        if (term == null) throw new IllegalArgumentException("용어 삭제 실패 : 용어를 찾을 수 없습니다.");
        else if (term.isDeleteStatus()) throw new IllegalArgumentException("용어 삭제 실패 : 이미 삭제 된 용어입니다.");

        term.approvalToTerm(approval);
        termWordMapper.deleteByTermId(approval.getTargetId()); // cascade?
        termMapper.deleteTerm(term);
    }

    @Transactional
    public void deleteTermWord(Approval approval) {
        termWordMapper.delete(approval.getTargetId());
        termMapper.updateTermByTermWord(approval);
    }

    @Transactional
    public Approval dtoToApproval(User user, TermDto termDto, int targetId) {  // UPDATE
        if (termDto.getEngName() == null) throw new IllegalArgumentException("결재 생성 실패 : 영문명이 공백입니다.");
        Term term = termMapper.getTermById(targetId);
        if (term == null) throw new IllegalArgumentException("결재 생성 실패 : 용어를 찾을 수 없습니다.");
        else if (term.isDeleteStatus()) throw new IllegalArgumentException("결재 생성 실패 : 삭제된 용어입니다.");

        Approval approval = Approval.builder()
                .createUser(user)
                .targetId(targetId)
                .wordType(WordType.TERM)
                .approvalType(ApprovalType.UPDATE)
                .build();
        if (Objects.equals(term.getEngName(), termDto.getEngName())) approval.setEngName(null);
        else approval.setEngName(termDto.getEngName());
        if (Objects.equals(term.getKorName(), termDto.getKorName())) approval.setKorName(null);
        else approval.setKorName(termDto.getKorName());
        if (Objects.equals(term.getShortName(), termDto.getShortName())) approval.setShortName(null);
        else approval.setShortName(termDto.getShortName());
        if (Objects.equals(term.getMeaning(), termDto.getMeaning())) approval.setMeaning(null);
        else approval.setMeaning(termDto.getMeaning());
        return approval;
    }

    @Transactional
    public Approval dtoToApproval(User user, TermDto termDto) {  // CREATE
        if (termDto.getEngName() == null) throw new IllegalArgumentException("결재 생성 실패 : 영문명이 공백입니다.");
        return Approval.builder()
                .createUser(user)
                .targetId(0)
                .approvalType(ApprovalType.CREATE)
                .wordType(WordType.TERM)            // headers
                .engName(termDto.getEngName())
                .korName(termDto.getKorName())
                .shortName(termDto.getShortName())
                .meaning(termDto.getMeaning())      // body
                .build();
    }

    @Transactional
    public Approval dtoToApproval(User user, int targetId) { // DELETE
        Term term = termMapper.getTermById(targetId);
        if (term == null) throw new IllegalArgumentException("결재 생성 실패 : 용어를 찾을 수 없습니다.");
        else if (term.isDeleteStatus()) throw new IllegalArgumentException("결재 생성 실패 : 이미 삭제된 용어입니다");
        return Approval.builder()
                .createUser(user)
                .targetId(targetId)
                .approvalType(ApprovalType.DELETE)
                .wordType(WordType.TERM)
                .build();
    }

    @Transactional
    public Approval dtoToApproval(User user, int termId, int wordId, ApprovalType approvalType) {  // CREATE TW
        Term term = termMapper.getTermById(termId);
        if (term == null) throw new IllegalArgumentException("결재 생성 실패 : 용어를 찾을 수 없습니다.");
        else if (term.isDeleteStatus()) throw new IllegalArgumentException("결재 생성 실패 : 이미 삭제된 용어입니다");

        Word word = wordMapper.getWordById(wordId);
        if (word == null) throw new IllegalArgumentException("결재 생성 실패 : 단어를 찾을 수 없습니다.");
        else if (word.isDeleteStatus()) throw new IllegalArgumentException("결재 생성 실패 : 이미 삭제된 단어입니다");

        if (approvalType != ApprovalType.CREATE && approvalType != ApprovalType.DELETE)
            throw new IllegalArgumentException("결재 생성 실패 : 잘못된 타입입니다.");

        return Approval.builder()
                .createUser(user)
                .targetId(termId)
                .slaveId(wordId)
                .approvalType(approvalType)
                .wordType(WordType.TERMWORD)            // headers
                .build();
    }

    @Transactional
    public void deleteTermDB(int termId) {
        termMapper.deleteTermDB(termId);
    }

    @Transactional(readOnly = true)
    public Term findById(int termId) {
        return termMapper.getTermById(termId);
    }

    @Transactional(readOnly = true)
    public List<Word> getWordListByTermId(int termId) {
        if (termMapper.getTermById(termId) == null) throw new IllegalArgumentException("용어 조회 실패 : 용어를 찾을 수 없습니다.");
        List<Integer> ids = termWordMapper.getWordIdListByTermId(termId);
        List<Word> res = new ArrayList<>();
        for (int id : ids) {
            res.add(wordMapper.getWordById(id));
        }
        return res;
    }

    @Transactional(readOnly = true)
    public List<Term> findByName(String name) {
        return termMapper.getTermsByName(name);
    }

    @Transactional(readOnly = true)
    public List<Term> getTermListByUserId(int userId) {
        return termMapper.getTermsByUserId(userId);
    }

    @Transactional
    public List<Term> getTerms() {
        return termMapper.getTerms();
    }
}
