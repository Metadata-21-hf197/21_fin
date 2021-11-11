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
    public void insertTermWord(Approval approval){
        if(termMapper.getTermById(approval.getTargetId()) == null){
            System.out.println("용어 단어 추가 실패 : 용어를 찾을 수 없습니다.");
            return;
        }
        if(wordMapper.getWordById(approval.getSlaveId()) == null){
            System.out.println("용어 단어 추가 실패 : 단어를 찾을 수 없습니다.");
            return;
        }
        termWordMapper.insert(approval.getTargetId(), approval.getSlaveId());
        termMapper.updateTermByTermWord(approval);
    }

    @Transactional
    public void updateTerm(Approval approval) {
        Term term = termMapper.getTermById(approval.getTargetId());
        if (term == null) {
            System.out.println("용어 수정 실패 : 용어를 찾을 수 없습니다.");
            return;
        }
        term.approvalToTerm(approval);
        termMapper.updateTerm(term);
    }

    @Transactional
    public void deleteTerm(Approval approval) {
        Term term = termMapper.getTermById(approval.getTargetId());
        if (term == null) {
            System.out.println("용어 삭제 실패 : 용어를 찾을 수 없습니다.");
            return;
        }
        term.approvalToTerm(approval);
        termMapper.deleteTerm(term);
        termWordMapper.deleteByTermId(approval.getApprovalId());
    }

    @Transactional
    public void deleteTermWord(Approval approval) {
        termWordMapper.delete(approval.getTargetId());
        termMapper.updateTermByTermWord(approval);
    }

    public List<Approval> dtoToApproval(User user, TermDto termDto, int targetId) {  // UPDATE
        Term term = termMapper.getTermById(targetId);
        if (term == null) {
            System.out.println("결재 추가 실패 : 용어를 찾을 수 없습니다.");
            return null;
        }
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


        List<Integer> termWords = termWordMapper.getWordIdListByTermId(targetId);
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
        termMapper.deleteTermDB(termId);
    }

    @Transactional(readOnly = true)
    public Term findById(int termId) {
        return termMapper.getTermById(termId);
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
