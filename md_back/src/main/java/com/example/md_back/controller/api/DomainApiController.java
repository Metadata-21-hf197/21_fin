package com.example.md_back.controller.api;

import com.example.md_back.dto.RequestDomainDto;
import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DomainApiController {

    @Autowired
    private DomainService domainService;

    @PostMapping("/domain/insert")
    public Map<String, Object> insert(@RequestBody RequestDomainDto requestDomainDto){ // 세션의 유저 정보 받아 옴
        domainService.insertDomain(requestDomainDto, null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    @PostMapping("/domain/{domainId}/addcode")
    public Map<String, Object> addCode(@PathVariable int domainId, @RequestBody RequestNamesDto requestNamesDto){
        domainService.addCode(domainId, requestNamesDto, null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    @PutMapping("/domain/{domainId}/update")
    public Map<String, Object> update(@PathVariable int domainId, @RequestBody RequestDomainDto requestDomainDto){ // 세션의 유저 정보 받아 옴
        domainService.updateDomain(domainId, requestDomainDto, null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    @PutMapping("/domain/{domainId}/update/{codeId}")
    public Map<String, Object> updateCode(@PathVariable int domainId, @PathVariable int codeId, @RequestBody RequestNamesDto requestNamesDto){
        domainService.updateCode(domainId, codeId, requestNamesDto, null); // 유저정보 추가 할것
        // return httpStatus
        return null;
    }
    
    @DeleteMapping("/domain/{domainId}/delete")
    public Map<String, Object> delete(@PathVariable int domainId){
        domainService.deleteDomain(domainId, null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    @DeleteMapping("/domain/{domainId}/delete/{codeId}")
    public Map<String, Object> deleteCode(@PathVariable int domainId, @PathVariable int codeId){
        domainService.deleteCode(domainId, codeId, null); // 유저정보 추가 할것
        // httpStatus return
        return null;
    }

    @DeleteMapping("/domain/{domainId}/deleteDB")
    public void deleteDB (@PathVariable int domainId){
        domainService.deleteDomainDB(domainId);
    }
}
