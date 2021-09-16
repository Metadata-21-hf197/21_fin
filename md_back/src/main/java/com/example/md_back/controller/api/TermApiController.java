package com.example.md_back.controller.api;

import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TermApiController {

    @Autowired
    private TermService termService;

    @PostMapping("/term")
    public int insert(@RequestBody RequestNamesDto requestNamesDto) { // 세션의 유저 정보 받아 옴
        termService.insertTerm(requestNamesDto, null); // 유저정보 추가 할것
        return HttpStatus.OK.value();
    }

    @PutMapping("/term/{termId}")
    public int update(@PathVariable int termId, @RequestBody RequestNamesDto requestNamesDto) { // 세션의 유저 정보 받아 옴
        termService.updateTerm(termId, requestNamesDto, null); // 유저정보 추가 할것
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/term/{termId}")
    public int delete(@PathVariable int termId) {
        termService.deleteTerm(termId, null); //유저정보 추가 할것
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/term/{termId}/deleteDB")
    public void deleteDB(@PathVariable int termId){
        termService.deleteTermDB(termId);
    }
}
