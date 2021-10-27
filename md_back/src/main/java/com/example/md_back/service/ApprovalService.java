package com.example.md_back.service;


import com.example.md_back.mappers.ApprovalMapper;
import com.example.md_back.model.Approval;
import com.example.md_back.model.User;
import com.example.md_back.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;
    @Autowired
    private ApprovalMapper approvalMapper;

    @Transactional
    public void insert(Approval approval) {
        approvalRepository.save(approval);
    }

    @Transactional
    public void insertApprovals(List<Approval> approvals) {
        approvalRepository.saveAll(approvals);
    }

    @Transactional
    public void update(Approval approval) {
        Approval found = approvalRepository.findById(approval.getApprovalId()).orElseThrow(() -> new IllegalArgumentException("결재 수정 실패 : 해당하는 결재가 없습니다."));
        if (found.isConfirmed()) {
            System.out.println("이미 결재되었습니다.");
            return;
        }
        // found.setFields(approval)
        approvalRepository.save(found);
    }

    @Transactional
    public void delete(int approvalId) {
        if (approvalRepository.findById(approvalId).orElseThrow(() -> new IllegalArgumentException("결재 삭제 실패 : 해당하는 결재가 없습니다.")).isConfirmed()) {
            System.out.println("이미 결재되었습니다.");
            return;
        }
        approvalRepository.deleteById(approvalId);
    }

    @Transactional
    public Approval confirm(int approvalId, User user){
        Approval found = approvalRepository.findById(approvalId).orElseThrow(() -> new IllegalArgumentException("결재 실패 : 해당하는 결재가 없습니다."));
        if (found.isConfirmed()) {
            System.out.println("이미 결재되었습니다.");
            return null;
        }
        found.setConfirmUser(user);
        found.setConfirmDate(null);
        approvalRepository.save(found);
        return found;
    }

    public List<Approval> getApprovalListByUserId(int userId) { return approvalMapper.getApprovalListByUserId(userId);}
}
