package com.example.md_back.controller.api;

import com.example.md_back.dto.RequestDomainDto;
import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class DomainApiController {

    @Autowired
    private DomainService domainService;

    @PostMapping("/domain")
    public int insert(@RequestBody RequestDomainDto requestDomainDto){ // 세션의 유저 정보 받아 옴
        domainService.insertDomain(requestDomainDto, null); // 유저정보 추가 할것
        return HttpStatus.OK.value();
    }

    @PostMapping("/domain/{domainId}/addCode")
    public int addCode(@PathVariable int domainId, @RequestBody RequestNamesDto requestNamesDto){
        domainService.addCode(domainId, requestNamesDto, null); // 유저정보 추가 할것
        return HttpStatus.OK.value();
    }

    @PutMapping("/domain/{domainId}")
    public int update(@PathVariable int domainId, @RequestBody RequestDomainDto requestDomainDto){ // 세션의 유저 정보 받아 옴
        domainService.updateDomain(domainId, requestDomainDto, null); // 유저정보 추가 할것
        return HttpStatus.OK.value();
    }

    @PutMapping("/domain/{domainId}/update/{codeId}")
    public int updateCode(@PathVariable int domainId, @PathVariable int codeId, @RequestBody RequestNamesDto requestNamesDto){
        domainService.updateCode(domainId, codeId, requestNamesDto, null); // 유저정보 추가 할것
        return HttpStatus.OK.value();
    }
    
    @DeleteMapping("/domain/{domainId}")
    public int delete(@PathVariable int domainId){
        domainService.deleteDomain(domainId, null); // 유저정보 추가 할것
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/domain/{domainId}/delete/{codeId}")
    public int deleteCode(@PathVariable int domainId, @PathVariable int codeId){
        domainService.deleteCode(domainId, codeId, null); // 유저정보 추가 할것
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/domain/{domainId}/deleteDB")
    public void deleteDB (@PathVariable int domainId){
        domainService.deleteDomainDB(domainId);
    }
}
