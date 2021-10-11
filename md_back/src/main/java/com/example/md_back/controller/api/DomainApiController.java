package com.example.md_back.controller.api;

import com.example.md_back.dto.DomainDto;
import com.example.md_back.dto.LoginDTO;
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
    public int insert(@RequestBody DomainDto domainDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.insertDomain(null);
        return HttpStatus.OK.value();
    }

    @PostMapping("/domain/{domainId}/addCode")
    public int addCode(@PathVariable int domainId, @RequestBody DomainDto domainDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.addCode(null);
        return HttpStatus.OK.value();
    }

    @PutMapping("/domain/{domainId}")
    public int update(@PathVariable int domainId, @RequestBody DomainDto domainDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.updateDomain(null);
        return HttpStatus.OK.value();
    }

    @PutMapping("/domain/{domainId}/update/{codeId}")
    public int updateCode(@PathVariable int domainId, @PathVariable int codeId, @RequestBody DomainDto domainDto, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.updateCode(null);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/domain/{domainId}")
    public int delete(@PathVariable int domainId, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.deleteDomain(null);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/domain/{domainId}/delete/{codeId}")
    public int deleteCode(@PathVariable int domainId, @PathVariable int codeId, @AuthenticationPrincipal LoginDTO loginDTO) {
        domainService.deleteCode(null);
        return HttpStatus.OK.value();
    }

    @DeleteMapping("/domain/{domainId}/deleteDB")
    public void deleteDB(@PathVariable int domainId) {
        domainService.deleteDomainDB(domainId);
    }
}
