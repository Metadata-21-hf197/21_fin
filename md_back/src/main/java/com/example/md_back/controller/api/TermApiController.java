package com.example.md_back.controller.api;

import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class TermApiController {

    @Autowired
    private TermService termService;

    @PostMapping("/term/insert")
    public Map<String, Object> insert(@RequestBody RequestNamesDto requestNamesDto) { // 세션의 유저 정보 받아 옴
        termService.insertTerm(requestNamesDto, null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    @PutMapping("/term/{termId}/update")
    public Map<String, Object> update(@PathVariable int termId, @RequestBody RequestNamesDto requestNamesDto) { // 세션의 유저 정보 받아 옴
        termService.updateTerm(termId, requestNamesDto, null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    @DeleteMapping("/term/{termId}/delete")
    public Map<String, Object> delete(@PathVariable int termId) {
        termService.deleteTerm(termId, null); //유저정보 추가 할것
        // httpStatus return
        return null;
    }

    @DeleteMapping("/term/{termId}/deleteDB")
    public void deleteDB(@PathVariable int termId){
        termService.deleteTermDB(termId);
    }
}
