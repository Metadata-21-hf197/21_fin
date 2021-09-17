package com.example.md_back.service;

import com.example.md_back.dto.RequestDomainDto;
import com.example.md_back.dto.RequestNamesDto;
import com.example.md_back.model.Code;
import com.example.md_back.model.Domain;
import com.example.md_back.model.User;
import com.example.md_back.repository.CodeRepository;
import com.example.md_back.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DomainService {

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private CodeRepository codeRepository;

    @Transactional
    public void insertDomain(RequestDomainDto requestDomainDto, User user) {
        Domain domain = Domain.builder()
                .shortName(requestDomainDto.getShortName())
                .engName(requestDomainDto.getEngName())
                .korName(requestDomainDto.getKorName())
                .banWord(requestDomainDto.isBanWord())
                .type(requestDomainDto.getType())
                .meaning(requestDomainDto.getMeaning())
                .creationUser(user)
                .deleteStatus(false)
                .build();
        domainRepository.save(domain);
    }

    @Transactional
    public void updateDomain(int domainId, RequestDomainDto requestDomainDto, User user) {
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new IllegalArgumentException("도메인 수정 실패 : 도메인을 찾을 수 없습니다."));
        domain.setShortName(requestDomainDto.getShortName());
        domain.setEngName(requestDomainDto.getEngName());
        domain.setKorName(requestDomainDto.getKorName());
        domain.setMeaning(requestDomainDto.getMeaning());
        domain.setType(requestDomainDto.getType());
        domain.setBanWord(requestDomainDto.isBanWord());
        domain.setModifyUser(user);
        domainRepository.save(domain);
    }

    @Transactional
    public void deleteDomain(int domainId, User user) {
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new IllegalArgumentException("도메인 삭제 실패 : 도메인을 찾을 수 없습니다."));
        domain.setDeleteStatus(true);
        domain.setModifyUser(user);
        domainRepository.save(domain);
    }

    @Transactional
    public void deleteDomainDB(int domainId) {
        domainRepository.deleteById(domainId);
    }

    @Transactional
    public void addCode(int domainId, RequestNamesDto requestNamesDto, User user) {
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new IllegalArgumentException("코드 추가 실패 : 도메인을 찾을 수 없습니다."));
        //if(domain.getCodes().contains(requestNamesDto.getEngName())) {
        //  return new IllegalArgumentException("코드 추가 실패 : 이미 존재하는 코드입니다.");
        //}
        Code code = Code.builder()
                .shortName(requestNamesDto.getShortName())
                .engName(requestNamesDto.getEngName())
                .korName(requestNamesDto.getKorName())
                .domain(domain)
                .build();
        codeRepository.save(code);
        domain.setModifyUser(user);
        domainRepository.save(domain);
    }

    @Transactional
    public void updateCode(int domainId, int codeId, RequestNamesDto requestNamesDto, User user) {
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new IllegalArgumentException("코드 수정 실패 : 도메인을 찾을 수 없습니다."));

        Code code = codeRepository.findById(codeId)
                .orElseThrow(() -> new IllegalArgumentException("코드 수정 실패 : 코드를 찾을 수 없습니다."));
        code.setShortName(requestNamesDto.getShortName());
        code.setEngName(requestNamesDto.getEngName());
        code.setKorName(requestNamesDto.getKorName());
        domain.setModifyUser(user);
        codeRepository.save(code);
        domainRepository.save(domain);
    }

    @Transactional
    public void deleteCode(int domainId, int codeId, User user) {
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(() -> new IllegalArgumentException("코드 삭제 실패 : 도메인을 찾을 수 없습니다."));
        domain.setModifyUser(user);
        codeRepository.deleteById(codeId);
        domainRepository.save(domain);
    }

    @Transactional(readOnly = true)
    public Domain findById(int domainId) {
        return domainRepository.findById(domainId)
                .orElseThrow(() -> new IllegalArgumentException("도메인 찾기 실패 : " + domainId));
    }

    @Transactional(readOnly = true)
    public List<Domain> findByShortName(String shortName) {
        return domainRepository.findByShortName(shortName);
    }

    @Transactional(readOnly = true)
    public List<Domain> findByEngName(String engName) {
        return domainRepository.findByEngName(engName);
    }

    @Transactional(readOnly = true)
    public List<Domain> findByKorName(String korName) {
        return domainRepository.findByKorName(korName);
    }

    @Transactional(readOnly = true)
    public Code findByIdCode(int codeId) {
        return codeRepository.findById(codeId)
                .orElseThrow(() -> new IllegalArgumentException("코드 찾기 실패 : " + codeId));
    }

    @Transactional(readOnly = true)
    public List<Domain> getDomains() {
        return domainRepository.getTrueDomains();
    }
}
