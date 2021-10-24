package com.example.md_back.service;

import com.example.md_back.model.*;
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
    public void insertDomain(Approval approval) {
        Domain domain = new Domain();
        domain.approvalToDomain(approval);
        domainRepository.save(domain);
    }

    @Transactional
    public void updateDomain(Approval approval) {
        Domain domain = domainRepository.findById(approval.getTargetId())
                .orElseThrow(() -> new IllegalArgumentException("도메인 수정 실패 : 도메인을 찾을 수 없습니다."));
        domain.approvalToDomain(approval);
        domainRepository.save(domain);
    }

    @Transactional
    public void deleteDomain(Approval approval) {
        Domain domain = domainRepository.findById(approval.getTargetId())
                .orElseThrow(() -> new IllegalArgumentException("도메인 삭제 실패 : 도메인을 찾을 수 없습니다."));
        domain.approvalToDomain(approval);
        domainRepository.save(domain);
    }

    @Transactional
    public void deleteDomainDB(int domainId) {
        domainRepository.deleteById(domainId);
    }

    @Transactional
    public void addCode(Approval approval) {
        Domain domain = domainRepository.findById(approval.getSlaveId())
                .orElseThrow(() -> new IllegalArgumentException("코드 추가 실패 : 도메인을 찾을 수 없습니다."));
        //if(domain.getCodes().contains(requestNamesDto.getEngName())) {
        //  return new IllegalArgumentException("코드 추가 실패 : 이미 존재하는 코드입니다.");
        //}
        Code code = new Code();
        code.approvalToCode(approval);
        codeRepository.save(code);
        domain.setModifyUser(approval.getCreateUser());
        domain.setModifyDate(approval.getCreateDate());
        domainRepository.save(domain);
    }

    @Transactional
    public void updateCode(Approval approval) {
        Domain domain = domainRepository.findById(approval.getSlaveId())
                .orElseThrow(() -> new IllegalArgumentException("코드 수정 실패 : 도메인을 찾을 수 없습니다."));

        Code code = codeRepository.findById(approval.getTargetId())
                .orElseThrow(() -> new IllegalArgumentException("코드 수정 실패 : 코드를 찾을 수 없습니다."));
        code.approvalToCode(approval);
        domain.setModifyUser(approval.getCreateUser());
        domain.setModifyDate(approval.getCreateDate());
        codeRepository.save(code);
        domainRepository.save(domain);
    }

    @Transactional
    public void deleteCode(Approval approval) {
        Domain domain = domainRepository.findById(approval.getSlaveId())
                .orElseThrow(() -> new IllegalArgumentException("코드 삭제 실패 : 도메인을 찾을 수 없습니다."));
        domain.setModifyUser(approval.getCreateUser());
        domain.setModifyDate(approval.getCreateDate());
        codeRepository.deleteById(approval.getTargetId());
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
    public List<Domain> findByName(String name) {
        return domainRepository.findByKorName(name);
    }

    @Transactional(readOnly = true)
    public Code findByIdCode(int codeId) {
        return codeRepository.findById(codeId)
                .orElseThrow(() -> new IllegalArgumentException("코드 찾기 실패 : " + codeId));
    }

    @Transactional(readOnly = true)
    public List<Domain> getDomains() {
        return domainRepository.getDomains();
    }
}
