package com.example.md_back.service;


import com.example.md_back.mappers.ApprovalMapper;
import com.example.md_back.model.Approval;
import com.example.md_back.model.ApprovalStatus;
import com.example.md_back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalMapper approvalMapper;

    @Transactional
    public void insert(Approval approval) {
        approvalMapper.insertApproval(approval);
    }

    @Transactional
    public void insertApprovals(List<Approval> approvals) {
        for (Approval a : approvals) {
            approvalMapper.insertApproval(a);
        }
    }

    @Transactional
    public void update(Approval approval) {
        Approval found = approvalMapper.getApprovalById(approval.getApprovalId());
        if (found == null) {
            throw new IllegalArgumentException("결재 수정 실패 : 해당하는 결재가 없습니다.");
        }
        if (found.isConfirmed()) {
            throw new IllegalArgumentException("결재 수정 실패 : 이미 결재되었습니다.");
        }
        approvalMapper.updateApproval(approval);
        // must not use
    }

    @Transactional
    public void delete(int approvalId) {
        Approval approval = approvalMapper.getApprovalById(approvalId);
        if (approval == null) {
            throw new IllegalArgumentException("결재 삭제 실패 : 해당하는 결재가 없습니다.");
        } else if (approval.isConfirmed()) {
            throw new IllegalArgumentException("결재 삭제 실패 : 이미 결재되었습니다.");
        }
        approvalMapper.deleteApproval(approvalId);
    }

    @Transactional
    public void deleteApprovals(List<Integer> ids) {
        for (int i : ids) {
            approvalMapper.deleteApproval(i);
        }
    }

    @Transactional
    public Approval confirm(int approvalId, User user, ApprovalStatus approvalStatus) {
        Approval found = approvalMapper.getApprovalById(approvalId);
        if (found == null) {
            throw new IllegalArgumentException("결재 처리 실패 : 해당하는 결재가 없습니다.");
        } else if (found.isConfirmed()) {
            throw new IllegalArgumentException("결재 처리 실패 : 이미 결재되었습니다.");
        }
        approvalMapper.confirmApproval(approvalId, user.getMemberId(), approvalStatus.getValue());
        return found;
    }

//    @Transactional
//    public Approval deny(int approvalId, User user) {
//        Approval found = approvalMapper.getApprovalById(approvalId);
//        if (found == null) {
//            System.out.println("결재 거절 실패 : 해당하는 결재가 없습니다.");
//            return null;
//        } else if (found.isConfirmed()) {
//            System.out.println("결재 거절 실패 : 이미 결재되었습니다.");
//            return null;
//        }
//        approvalMapper.denyApproval(approvalId, user.getMemberId());
//        return found;
//    }

    @Transactional(readOnly = true)
    public Approval getApprovalById(int id) {
        return approvalMapper.getApprovalById(id);
    }

    @Transactional(readOnly = true)
    public List<Approval> getApprovals(ApprovalStatus approvalStatus) {
        return approvalMapper.getApprovals(approvalStatus);
    }

    @Transactional(readOnly = true)
    public List<Approval> getApprovalsByCreateUserId(int userId) {
        return approvalMapper.getApprovalsByCreateUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Approval> getApprovalsByConfirmUser(int userId) {
        return approvalMapper.getApprovalsByConfirmUserId(userId);
    }

}
