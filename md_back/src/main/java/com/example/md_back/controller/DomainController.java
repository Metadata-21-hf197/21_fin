package com.example.md_back.controller;

import com.example.md_back.model.Domain;
import com.example.md_back.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/mms")
public class DomainController {

    @Autowired
    private DomainService domainService;

    public String domainForm(){
        return null;
    }

    /** 도메인 추가 시 권한 확인, 세션의 유저 정보 받아서 서비스로 리턴도 해줘야함
     * @param domain, principal
     * @return
     */
    @PostMapping("/domain")
    public Map<String, Object> insert(@RequestBody Domain domain){ // 세션의 유저 정보 받아 옴
        // domain 객체 받아옴
        domainService.insertDomain(domain); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    /** 도메인 수정 시 권한 확인, 세션의 유저 정보 받아서 서비스로 리턴도 해줘야함
     * @param domainId, domain, principal
     * @return
     */
    @PutMapping("/domain/{domainId}")
    public Map<String, Object> update(@PathVariable int domainId, @RequestBody Domain domain){ // 세션의 유저 정보 받아 옴
        // domain 객체 받아옴
        domainService.updateDomain(domainId, domain); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    /** 도메인 삭제 시 권한 확인
     * @param domainId
     * @return
     */
    @DeleteMapping("/domain/{domainId}")
    public Map<String, Object> delete(@PathVariable int domainId){
        domainService.deleteDomain(domainId);
        // httpStatus return
        return null;
    }
}
