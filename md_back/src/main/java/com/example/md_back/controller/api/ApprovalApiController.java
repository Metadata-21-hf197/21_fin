package com.example.md_back.controller.api;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.model.Approval;
import com.example.md_back.model.ApprovalType;
import com.example.md_back.model.WordType;
import com.example.md_back.service.ApprovalService;
import com.example.md_back.service.DomainService;
import com.example.md_back.service.TermService;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ApprovalApiController {

    @Autowired
    private ApprovalService approvalService;

    @Autowired
    private WordService wordService;

    @Autowired
    private TermService termService;

    @Autowired
    private DomainService domainService;

    @PutMapping("/approval/{approvalId}")
    public String approvalConfirm(@PathVariable int approvalId, @AuthenticationPrincipal LoginDTO loginDTO) {
        Approval approval = approvalService.confirm(approvalId, loginDTO.getUser());
        if (approval != null) {
            if (approval.getWordType() == WordType.WORD) {
                if (approval.getApprovalType() == ApprovalType.CREATE)
                    wordService.insertWord(approval);
                if (approval.getApprovalType() == ApprovalType.UPDATE)
                    wordService.updateWord(approval);
                if (approval.getApprovalType() == ApprovalType.DELETE)
                    wordService.deleteWord(approval);
            }

            if (approval.getWordType() == WordType.TERM) {
                if (approval.getApprovalType() == ApprovalType.CREATE)
                    termService.insertTerm(approval);
                if (approval.getApprovalType() == ApprovalType.UPDATE)
                    termService.updateTerm(approval);
                if (approval.getApprovalType() == ApprovalType.DELETE)
                    termService.deleteTerm(approval);
            }

            if (approval.getWordType() == WordType.DOMAIN) {
                if (approval.getApprovalType() == ApprovalType.CREATE)
                    domainService.insertDomain(approval);
                if (approval.getApprovalType() == ApprovalType.UPDATE)
                    domainService.updateDomain(approval);
                if (approval.getApprovalType() == ApprovalType.DELETE)
                    domainService.updateDomain(approval);
            }

            if (approval.getWordType() == WordType.CODE) {
                if (approval.getApprovalType() == ApprovalType.CREATE)
                    domainService.addCode(approval);
                if (approval.getApprovalType() == ApprovalType.UPDATE)
                    domainService.updateCode(approval);
                if (approval.getApprovalType() == ApprovalType.DELETE)
                    domainService.deleteCode(approval);
            }
        }
        return "/approval";
    }

    @DeleteMapping("/approval/{id}")
    public String approvalDelete(@PathVariable int id) {
        // delete by id
        return "/approval";
    }
}
