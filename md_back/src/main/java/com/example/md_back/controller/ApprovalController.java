package com.example.md_back.controller;

import com.example.md_back.model.ApprovalStatus;
import com.example.md_back.service.ApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApprovalController {

    @Autowired
    private ApprovalService approvalService;

    @GetMapping("/table/approval")
    public Map<String, Object> home(){
        Map<String, Object> res = new HashMap<>();
        res.put("Approvals", approvalService.getApprovals(ApprovalStatus.Pending));
        return res;
    }

    @GetMapping("/table/approval/insert")
    public String insertForm(){
        return "approval/insertForm";
    }

    @GetMapping("/table/approval/update")
    public String updateForm(){
        Map <String ,Object> res = new HashMap<>();
        return "approval/updateForm";
    }

    @GetMapping("/table/approval/delete")
    public String deleteForm(){
        return "approval/deleteForm";
    }

    @GetMapping("/table/approval/{id}")
    public Map<String, Object> approvalDetail(@PathVariable int id){
        Map<String, Object> res = new HashMap<>();
        res.put("url", "approval/detail");
        res.put("approval", approvalService.getApprovalById(id));
        return res;

    }
}
