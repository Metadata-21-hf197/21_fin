package com.example.md_back.controller;

import com.example.md_back.model.ApprovalStatus;
import com.example.md_back.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @GetMapping("/table/approval")
    public String home(Model model){
        model.addAttribute("approvals", approvalService.getApprovals(ApprovalStatus.Pending));
        return "table/approval";
    }

    @GetMapping("/table/approval/insert")
    public String insertForm(){
        return "approval/insertForm";
    }

    @GetMapping("/table/approval/update")
    public String updateForm(){
        return "approval/updateForm";
    }

    @GetMapping("/table/approval/delete")
    public String deleteForm(){
        return "approval/deleteForm";
    }

    @GetMapping("/table/approval/{id}")
    public String approvalDetail(@PathVariable int id, Model model){
        model.addAttribute("approval", approvalService.getApprovalById(id));
        return "approval/detail";
    }
}
