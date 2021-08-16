package com.example.md_back.controller;

import com.example.md_back.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/mms")
public class TermController {

    @Autowired
    private TermService termService;

    @GetMapping("/term/insert")
    @ResponseBody
    public String termForm(){
        return "term/form";
    }

    /** 용어 추가 시 권한 확인, 세션의 유저 정보 받아서 서비스로 리턴도 해줘야함
     * @param
     * @return
     */
    @PostMapping("/term/insert")
    public Map<String, Object> insert(){ // 세션의 유저 정보 받아 옴
        // term 객체 받아옴
        termService.insertTerm(null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    /** 용어 수정 시 권한 확인, 세션의 유저 정보 받아서 서비스로 리턴도 해줘야함
     * 삭제도 이 곳에서 처리, deleteStatus = True로 update
     * @param termId, term, principal
     * @return
     */
    @PutMapping("/term/update/{termId}")
    public Map<String, Object> update(@PathVariable int termId){ // 세션의 유저 정보 받아 옴
        // term 객체 받아옴
        termService.updateTerm(termId,null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    /** 용어 삭제 시 권한 확인
     * DB 에서 용어 삭제 시 사용
     * @param termId
     * @return
     */
    @DeleteMapping("/term/delete/{termId}")
    public Map<String, Object> delete(@PathVariable int termId){
        termService.deleteTerm(termId);
        // httpStatus return
        return null;
    }
}
