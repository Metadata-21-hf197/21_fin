package com.example.md_back.service;

import com.example.md_back.dto.CodeDto;
import com.example.md_back.dto.DomainDto;
import com.example.md_back.mappers.CodeMapper;
import com.example.md_back.mappers.DomainMapper;
import com.example.md_back.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class DomainService {

    @Autowired
    private DomainMapper domainMapper;

    @Autowired
    private CodeMapper codeMapper;

    @Transactional
    public void insertDomain(Approval approval) {
        Domain domain = new Domain();
        domain.approvalToDomain(approval);
        domainMapper.insertDomain(domain);
    }

    @Transactional
    public void updateDomain(Approval approval) {
        Domain domain = domainMapper.getDomainById(approval.getTargetId());
        if (domain == null) throw new IllegalArgumentException("도메인 수정 실패 : 도메인을 찾을 수 없습니다.");
        else if (domain.isDeleteStatus()) throw new IllegalArgumentException("도메인 수정 실패 : 삭제된 도메인입니다.");

        domain.approvalToDomain(approval);
        domainMapper.updateDomain(domain);
    }

    @Transactional
    public void deleteDomain(Approval approval) {
        Domain domain = domainMapper.getDomainById(approval.getTargetId());
        if (domain == null) throw new IllegalArgumentException("도메인 삭제 실패 : 도메인을 찾을 수 없습니다.");
        else if (domain.isDeleteStatus()) throw new IllegalArgumentException("도메인 삭제 실패 : 삭제된 도메인입니다.");

        domain.approvalToDomain(approval);
        codeMapper.deleteCodeByDomainId(approval.getTargetId()); // CASCADE?
        domainMapper.deleteDomain(domain);
    }

    @Transactional
    public void deleteDomainDB(int domainId) {
        domainMapper.deleteDomainDB(domainId);
    }

    @Transactional
    public void addCode(Approval approval) {
        Domain domain = domainMapper.getDomainById(approval.getSlaveId());
        if (domain == null) throw new IllegalArgumentException("코드 추가 실패 : 도메인을 찾을 수 없습니다.");
        else if (domain.isDeleteStatus()) throw new IllegalArgumentException("코드 추가 실패 : 삭제된 도메인입니다.");

        Code code = new Code();
        code.approvalToCode(approval);
        code.setDomain(domain); // ????
        codeMapper.insertCode(code);
        domainMapper.updateDomainByCode(approval);
    }

    @Transactional
    public void updateCode(Approval approval) {
        Domain domain = domainMapper.getDomainById(approval.getSlaveId());
        if (domain == null) throw new IllegalArgumentException("코드 수정 실패 : 도메인을 찾을 수 없습니다.");
        else if (domain.isDeleteStatus()) throw new IllegalArgumentException("코드 수정 실패 : 삭제된 도메인입니다.");

        Code code = codeMapper.getCodeById(approval.getTargetId());
        if (code == null) throw new IllegalArgumentException("코드 수정 실패 : 코드를 찾을 수 없습니다.");

        code.approvalToCode(approval);
        code.setDomain(domain); // ???
        codeMapper.updateCode(code);
        domainMapper.updateDomainByCode(approval);
    }

    @Transactional
    public void deleteCode(Approval approval) {
        Domain domain = domainMapper.getDomainById(approval.getSlaveId());
        if (domain == null) throw new IllegalArgumentException("코드 삭제 실패 : 도메인을 찾을 수 없습니다.");

        codeMapper.deleteCode(approval.getTargetId());
        domainMapper.updateDomainByCode(approval);
    }

    @Transactional
    public Approval dtoToApproval(User user, DomainDto domainDto, int targetId) {  // UPDATE DOMAIN
        if (domainDto.getEngName() == null)throw new IllegalArgumentException("결재 생성 실패 : 영문명이 공백입니다.");
        Domain domain = domainMapper.getDomainById(targetId);
        if (domain == null) throw new IllegalArgumentException("결재 생성 실패 : 도메인을 찾을 수 없습니다.");
        else if (domain.isDeleteStatus()) throw new IllegalArgumentException("결재 생성 실패 : 삭제된 도메인입니다.");

        Approval approval = Approval.builder()
                .createUser(user)
                .targetId(targetId)
                .approvalType(ApprovalType.UPDATE)
                .wordType(WordType.DOMAIN)
                .build();

        if (Objects.equals(domain.getEngName(), domainDto.getEngName())) approval.setEngName(null);
        else approval.setEngName(domainDto.getEngName());
        if (Objects.equals(domain.getKorName(), domainDto.getKorName())) approval.setKorName(null);
        else approval.setKorName(domainDto.getKorName());
        if (Objects.equals(domain.getShortName(), domainDto.getShortName())) approval.setShortName(null);
        else approval.setShortName(domainDto.getShortName());
        if (Objects.equals(domain.getMeaning(), domainDto.getMeaning())) approval.setMeaning(null);
        else approval.setMeaning(domainDto.getMeaning());

        return approval;
    }

    @Transactional
    public Approval dtoToApproval(User user, CodeDto codeDto, int targetId, int slaveId) {  // UPDATE CODE
        if (codeDto.getEngName() == null)throw new IllegalArgumentException("결재 생성 실패 : 영문명이 공백입니다.");
        Domain domain = domainMapper.getDomainById(slaveId);
        if (domain == null) throw new IllegalArgumentException("결재 생성 실패 : 도메인을 찾을 수 없습니다.");
        else if (domain.isDeleteStatus()) throw new IllegalArgumentException("결재 생성 실패 : 삭제된 도메인입니다.");

        Code code = codeMapper.getCodeById(targetId);
        if (code == null) throw new IllegalArgumentException("결재 생성 실패 : 코드를 찾을 수 없습니다.");

        Approval approval = Approval.builder()
                .createUser(user)
                .targetId(targetId)
                .slaveId(slaveId)
                .approvalType(ApprovalType.UPDATE)
                .wordType(WordType.CODE)
                .build();

        if (Objects.equals(code.getEngName(), codeDto.getEngName())) approval.setEngName(null);
        else approval.setEngName(codeDto.getEngName());
        if (Objects.equals(code.getKorName(), codeDto.getKorName())) approval.setKorName(null);
        else approval.setKorName(codeDto.getKorName());
        if (Objects.equals(code.getShortName(), codeDto.getShortName())) approval.setShortName(null);
        else approval.setShortName(codeDto.getShortName());
        return approval;
    }

    @Transactional
    public Approval dtoToApproval(User user, DomainDto domainDto) { // CREATE DOMAIN
        if (domainDto.getEngName() == null)throw new IllegalArgumentException("결재 생성 실패 : 영문명이 공백입니다.");
        return Approval.builder()
                .createUser(user)
                .targetId(0)
                .approvalType(ApprovalType.CREATE)
                .wordType(WordType.DOMAIN)
                .engName(domainDto.getEngName())
                .korName(domainDto.getKorName())
                .shortName(domainDto.getShortName())
                .meaning(domainDto.getMeaning())
                .build();
    }

    @Transactional
    public Approval dtoToApproval(User user, CodeDto codeDto, int slaveId) { // CREATE CODE
        if (codeDto.getEngName() == null)throw new IllegalArgumentException("결재 생성 실패 : 영문명이 공백입니다.");
        Domain domain = domainMapper.getDomainById(slaveId);
        if (domain == null) throw new IllegalArgumentException("결재 생성 실패 : 도메인을 찾을 수 없습니다.");
        else if (domain.isDeleteStatus()) throw new IllegalArgumentException("결재 생성 실패 : 삭제된 도메인입니다.");
        return Approval.builder()
                .createUser(user)
                .targetId(0)
                .slaveId(slaveId)
                .approvalType(ApprovalType.CREATE)
                .wordType(WordType.CODE)
                .engName(codeDto.getEngName())
                .korName(codeDto.getKorName())
                .shortName(codeDto.getShortName())
                .build();
    }

    @Transactional
    public Approval dtoToApproval(User user, int targetId) { // DELETE DOMAIN
        Domain domain = domainMapper.getDomainById(targetId);
        if (domain == null) throw new IllegalArgumentException("결재 생성 실패 : 도메인을 찾을 수 없습니다.");
        else if (domain.isDeleteStatus()) throw new IllegalArgumentException("결재 생성 실패 : 삭제된 도메인입니다.");
        return Approval.builder()
                .createUser(user)
                .targetId(targetId)
                .approvalType(ApprovalType.DELETE)
                .wordType(WordType.DOMAIN)
                .build();
    }

    @Transactional
    public Approval dtoToApproval(User user, int targetId, int slaveId) { // DELETE CODE
        Domain domain = domainMapper.getDomainById(slaveId);
        if (domain == null) throw new IllegalArgumentException("결재 생성 실패 : 도메인을 찾을 수 없습니다.");
        else if (domain.isDeleteStatus()) throw new IllegalArgumentException("결재 생성 실패 : 삭제된 도메인입니다.");
        else if (codeMapper.getCodeById(targetId) == null)
            throw new IllegalArgumentException("결재 생성 실패 : 코드를 찾을 수 없습니다.");
        return Approval.builder()
                .createUser(user)
                .targetId(targetId)
                .slaveId(slaveId)
                .approvalType(ApprovalType.DELETE)
                .wordType(WordType.CODE)
                .build();
    }


    @Transactional(readOnly = true)
    public Domain findById(int domainId) {
        return domainMapper.getDomainById(domainId);
    }

    @Transactional(readOnly = true)
    public List<Domain> findByName(String name) {
        return domainMapper.getDomainsByName(name);
    }

    @Transactional(readOnly = true)
    public Code findCodeById(int codeId) {
        return codeMapper.getCodeById(codeId);
    }

    @Transactional(readOnly = true)
    public List<Domain> getDomainListByUserid(int userId) {
        return domainMapper.getDomainsByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<Domain> getDomains() {
        return domainMapper.getDomains();
    }
}
