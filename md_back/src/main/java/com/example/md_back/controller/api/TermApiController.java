package com.example.md_back.controller.api;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.service.TermService;
import jdk.jpackage.internal.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class TermApiController {

    @Autowired
    private TermService termService;

    @PostMapping("/term")
    public int insert(@RequestBody RequestNamesDto requestNamesDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        termService.insertTerm(requestNamesDto, loginDTO.getUser());
        return HttpStatus.OK.value();
    }

    @PutMapping("/term/{termId}")
    public int update(@PathVariable int termId, @RequestBody RequestNamesDto requestNamesDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        termService.updateTerm(termId, requestNamesDto, loginDTO.getUser());
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/term/{termId}")
    public int delete(@PathVariable int termId, @AuthenticationPrincipal LoginDTO loginDTO) {
        termService.deleteTerm(termId, loginDTO.getUser());
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/term/{termId}/deleteDB")
    public void deleteDB(@PathVariable int termId){
        termService.deleteTermDB(termId);
    }
}
