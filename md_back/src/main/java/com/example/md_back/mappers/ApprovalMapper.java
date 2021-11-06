package com.example.md_back.mappers;

import com.example.md_back.model.Approval;
import com.example.md_back.model.ApprovalStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApprovalMapper {

    Approval getApprovalById(int approvalId);
    List<Approval> getApprovals(ApprovalStatus approvalStatus);
    List<Approval> getApprovalsByCreateUserId(int userId);
    List<Approval> getApprovalsByConfirmUserId(int userId);
    void insertApproval(Approval approval);
    void updateApproval(Approval approval);
    void deleteApproval(int approvalId);
    void confirmApproval(int approvalId, int confirmUserId, int approvalStatus);
}
