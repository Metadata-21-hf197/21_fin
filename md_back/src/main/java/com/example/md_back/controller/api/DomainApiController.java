package com.example.md_back.controller.api;

import com.example.md_back.dto.LoginDTO;
import com.example.md_back.dto.RequestDomainDto;
import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.service.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class DomainApiController {

    @Autowired
    private DomainService domainService;

    @PostMapping("/domain")
    public int insert(@RequestBody RequestDomainDto requestDomainDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.insertDomain(requestDomainDto, loginDTO.getUser());
        return HttpStatus.OK.value();
    }

    @PostMapping("/domain/{domainId}/addCode")
    public int addCode(@PathVariable int domainId, @RequestBody RequestNamesDto requestNamesDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.addCode(domainId, requestNamesDto, loginDTO.getUser());
        return HttpStatus.OK.value();
    }

    @PutMapping("/domain/{domainId}")
    public int update(@PathVariable int domainId, @RequestBody RequestDomainDto requestDomainDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.updateDomain(domainId, requestDomainDto, loginDTO.getUser());
        return HttpStatus.OK.value();
    }

    @PutMapping("/domain/{domainId}/update/{codeId}")
    public int updateCode(@PathVariable int domainId, @PathVariable int codeId, @RequestBody RequestNamesDto requestNamesDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.updateCode(domainId, codeId, requestNamesDto, loginDTO.getUser());
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/domain/{domainId}")
    public int delete(@PathVariable int domainId, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.deleteDomain(domainId, loginDTO.getUser());
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/domain/{domainId}/delete/{codeId}")
    public int deleteCode(@PathVariable int domainId, @PathVariable int codeId, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.deleteCode(domainId, codeId, loginDTO.getUser());
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/domain/{domainId}/deleteDB")
    public void deleteDB(@PathVariable int domainId) {
        domainService.deleteDomainDB(domainId);
    }
}
