package com.example.md_back.service;

import com.example.md_back.model.Domain;
import com.example.md_back.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DomainService {

    @Autowired
    private DomainRepository domainRepository;

    /** 유저 정보도 받아서 도메인의 작성자에 추가해야함
     * @param domain, user
     */
    public void insertDomain(Domain domain){
        // domain.setFields();
        domainRepository.save(domain);
    }

    /** 세션의 유저정보 받아서 도메인의 수정자에 추가해야함
     * @param domainId, requestDomain, user
     */
    public void updateDomain(int domainId, Domain requestDomain){
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("도메인 수정 실패 : 도메인을 찾을 수 없습니다.");
                });
        // domain.setFields(requestDomain.getFields);
    }

    public void deleteDomain(int domainId){
        domainRepository.deleteById(domainId);
    }
}
