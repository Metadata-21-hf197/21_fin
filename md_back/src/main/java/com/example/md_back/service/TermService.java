package com.example.md_back.service;

import com.example.md_back.dto.TermDto;
import com.example.md_back.model.*;
import com.example.md_back.repository.TermRepository;
import com.example.md_back.repository.TermWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TermService {

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private TermWordRepository termWordRepository;

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

    public List<Approval> dtoToApproval(User user, TermDto termDto, int targetId) {  // UPDATE
        Term term = termRepository.findById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("결재 추가 실패 : 용어를 찾을 수 없습니다."));
        Approval body = new Approval();
        List<Approval> approvals = new ArrayList<>();
        body.setCreateUser(user);
        body.setTargetId(targetId);
        body.setApprovalType(ApprovalType.UPDATE);
        body.setWordType(WordType.TERM);
        Approval relation = body;
        // headers

        if (body.getEngName() != null || body.getKorName() != null || body.getShortName() != null || body.getMeaning() != null) {
            if (Objects.equals(term.getEngName(), termDto.getEngName())) body.setEngName(null);
            else body.setEngName(termDto.getEngName());

            if (Objects.equals(term.getKorName(), termDto.getKorName())) body.setKorName(null);
            else body.setKorName(termDto.getKorName());

            if (Objects.equals(term.getShortName(), termDto.getShortName())) body.setShortName(null);
            else body.setShortName(termDto.getShortName());

            if (Objects.equals(term.getMeaning(), termDto.getMeaning())) body.setMeaning(null);
            else body.setMeaning(termDto.getMeaning());

            approvals.add(body);
        }// body


        List<Integer> termWords = termWordRepository.getWordIdByTermId(targetId);
        for (int i : termWords) {
            if (!termDto.getWords().contains(i)) {
                relation.setApprovalType(ApprovalType.DELETE);
                relation.setSlaveId(i);
                approvals.add(relation);
            }
        } // delete

        for (int i : termDto.getWords()) {
            if (!termWords.contains(i)) { // isNotExist works?
                relation.setApprovalType(ApprovalType.CREATE);
                relation.setSlaveId(i);
                approvals.add(relation);
            }
        } // create
        // TermWord logics

        return approvals;
    }

    public Approval dtoToApproval(User user, TermDto termDto) {  // CREATE
        Approval approval = new Approval();
        approval.setCreateUser(user);
        approval.setTargetId(0);
        approval.setApprovalType(ApprovalType.CREATE);
        approval.setWordType(WordType.TERM);
        // headers

        approval.setEngName(termDto.getEngName());
        approval.setKorName(termDto.getKorName());
        approval.setShortName(termDto.getShortName());
        approval.setMeaning(termDto.getMeaning());
        // body
        return approval;
    }

    public Approval dtoToApproval(User user, int targetId) { // DELETE
        Approval approval = new Approval();
        approval.setCreateUser(user);
        approval.setTargetId(targetId);
        approval.setApprovalType(ApprovalType.DELETE);
        approval.setWordType(WordType.TERM);
        // headers
        return approval;
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
    public List<Term> findByName(String name) {
        return termRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public List<Term> getTermListByUserId(int userId) {
        return termRepository.findByCreateUserOrModifyUser(userId);
    }

    @Transactional
    public List<Term> getTerms() {
        return termRepository.getTerms();
    }
}
