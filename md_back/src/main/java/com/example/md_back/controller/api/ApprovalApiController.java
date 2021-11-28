package com.example.md_back.controller.api;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.model.Approval;
import com.example.md_back.model.ApprovalStatus;
import com.example.md_back.model.ApprovalType;
import com.example.md_back.model.WordType;
import com.example.md_back.service.ApprovalService;
import com.example.md_back.service.DomainService;
import com.example.md_back.service.TermService;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApprovalApiController {

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private WordService wordService;

    @Autowired
    private TermService termService;

    @Autowired
    private DomainService domainService;

    @GetMapping("/table/approval/{id}")
    public Map<String, Object> approvalDetail(@PathVariable int id) {
        Map<String, Object> res = new HashMap<>();
        res.put("url", "approval/detail");
        Approval approval = approvalService.getApprovalById(id);
        Object o;
        if (approval.getWordType() == WordType.WORD) o = wordService.findById(approval.getTargetId());
        else if (approval.getWordType() == WordType.TERM) o = termService.findById(approval.getTargetId());
        else if (approval.getWordType() == WordType.DOMAIN) o = domainService.findById(approval.getTargetId());
        else if (approval.getWordType() == WordType.CODE) o = domainService.findCodeById(approval.getTargetId());
        else if (approval.getWordType() == WordType.TERMWORD) o = termService.findById(approval.getTargetId());
        else throw new IllegalArgumentException("결재 처리 실패 : 잘못된 결재입니다.");
        res.put("approval", approval);
        res.put("basic", o);
        return res;
    }


    @PutMapping("/approval/confirm/{approvalId}")
    public int approvalConfirm(@PathVariable int approvalId, @AuthenticationPrincipal LoginDTO loginDTO) {
        Approval approval = approvalService.confirm(approvalId, loginDTO.getUser(), ApprovalStatus.Confirm);
        if (approval.getWordType() == WordType.WORD) {
            if (approval.getApprovalType() == ApprovalType.CREATE)
                wordService.insertWord(approval);
            else if (approval.getApprovalType() == ApprovalType.UPDATE)
                wordService.updateWord(approval);
            else if (approval.getApprovalType() == ApprovalType.DELETE)
                wordService.deleteWord(approval);
        } else if (approval.getWordType() == WordType.TERM) {
            if (approval.getApprovalType() == ApprovalType.CREATE)
                termService.insertTerm(approval);
            else if (approval.getApprovalType() == ApprovalType.UPDATE)
                termService.updateTerm(approval);
            else if (approval.getApprovalType() == ApprovalType.DELETE)
                termService.deleteTerm(approval);
        } else if (approval.getWordType() == WordType.DOMAIN) {
            if (approval.getApprovalType() == ApprovalType.CREATE)
                domainService.insertDomain(approval);
            else if (approval.getApprovalType() == ApprovalType.UPDATE)
                domainService.updateDomain(approval);
            else if (approval.getApprovalType() == ApprovalType.DELETE)
                domainService.deleteDomain(approval);
        } else if (approval.getWordType() == WordType.CODE) {
            if (approval.getApprovalType() == ApprovalType.CREATE)
                domainService.addCode(approval);
            else if (approval.getApprovalType() == ApprovalType.UPDATE)
                domainService.updateCode(approval);
            else if (approval.getApprovalType() == ApprovalType.DELETE)
                domainService.deleteCode(approval);
        } else if (approval.getWordType() == WordType.TERMWORD) {
            if (approval.getApprovalType() == ApprovalType.CREATE)
                termService.insertTermWord(approval);
            else if (approval.getApprovalType() == ApprovalType.DELETE)
                termService.deleteTermWord(approval);
        }
        return HttpStatus.OK.value();
    }

    @PutMapping("/approval/deny/{approvalId}")
    public int approvalDeny(@PathVariable int approvalId, @AuthenticationPrincipal LoginDTO loginDTO) {
        approvalService.confirm(approvalId, loginDTO.getUser(), ApprovalStatus.Denied);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/approval/{approvalId}")
    public int approvalDelete(@PathVariable int approvalId, @AuthenticationPrincipal LoginDTO loginDTO) {
        approvalService.delete(approvalId);
        return HttpStatus.OK.value();
    }
}
