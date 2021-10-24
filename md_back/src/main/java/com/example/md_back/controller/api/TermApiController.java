package com.example.md_back.controller.api;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.dto.TermDto;
import com.example.md_back.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class TermApiController {

    @Autowired
    private TermService termService;

    @PostMapping("/term")
    public int insert(@RequestBody TermDto termDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        termService.insertTerm(null);
        return HttpStatus.OK.value();
    }

    @PutMapping("/term/{termId}")
    public int update(@PathVariable int termId, @RequestBody TermDto termDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        termService.updateTerm(null);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/term/{termId}")
    public int delete(@PathVariable int termId, @AuthenticationPrincipal LoginDTO loginDTO) {
        termService.deleteTerm(null);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/term/{termId}/deleteDB")
    public void deleteDB(@PathVariable int termId){
        termService.deleteTermDB(termId);
    }
}
