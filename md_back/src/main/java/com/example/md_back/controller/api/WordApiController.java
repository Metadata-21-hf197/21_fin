package com.example.md_back.controller.api;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.dto.WordDto;
import com.example.md_back.model.Approval;
import com.example.md_back.service.ApprovalService;
import com.example.md_back.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class WordApiController {

    @Autowired
    private WordService wordService;

    @Autowired
    private ApprovalService approvalService;

    @PostMapping("/word")
    public int insert(@RequestBody WordDto wordDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        Approval approval = wordService.dtoToApproval(loginDTO.getUser(), wordDto);
        approvalService.insert(approval);
        return HttpStatus.OK.value();
    }

    @PutMapping("/word/{wordId}")
    public int update(@PathVariable int wordId, @RequestBody WordDto wordDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        Approval approval = wordService.dtoToApproval(loginDTO.getUser(), wordDto, wordId);
        approvalService.insert(approval);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/word/{wordId}")
    public int delete(@PathVariable int wordId, @AuthenticationPrincipal LoginDTO loginDTO) {
        Approval approval = wordService.dtoToApproval(loginDTO.getUser(), wordId);
        approvalService.insert(approval);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/word/{wordId}/deleteDB")
    public void deleteDB(@PathVariable int wordId){
        wordService.deleteWordDB(wordId);
    }
}
