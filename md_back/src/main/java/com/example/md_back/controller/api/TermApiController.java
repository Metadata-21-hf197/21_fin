package com.example.md_back.controller.api;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.dto.TermDto;
import com.example.md_back.model.Approval;
import com.example.md_back.service.ApprovalService;
import com.example.md_back.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TermApiController {

    @Autowired
    private TermService termService;

    @Autowired
    private ApprovalService approvalService;

    @PostMapping("/term")
    public int insert(@RequestBody TermDto termDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        Approval approval = termService.dtoToApproval(loginDTO.getUser(), termDto);
        approvalService.insert(approval);
        return HttpStatus.OK.value();
    }

    @PutMapping("/term/{termId}")
    public int update(@PathVariable int termId, @RequestBody TermDto termDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        List<Approval> approvals = termService.dtoToApproval(loginDTO.getUser(), termDto, termId);
        approvalService.insertApprovals(approvals);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/term/{termId}")
    public int delete(@PathVariable int termId, @AuthenticationPrincipal LoginDTO loginDTO) {
        Approval approval = termService.dtoToApproval(loginDTO.getUser(), termId);
        approvalService.insert(approval);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/term/{termId}/deleteDB")
    public void deleteDB(@PathVariable int termId) {
        termService.deleteTermDB(termId);
    }
}
