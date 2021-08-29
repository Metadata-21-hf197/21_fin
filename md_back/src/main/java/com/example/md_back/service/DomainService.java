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

import java.util.List;

@Service
public class DomainService {

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private CodeRepository codeRepository;

    public void insertDomain(RequestDomainDto requestDomainDto, User user){
        Domain domain = Domain.builder()
                .shortName(requestDomainDto.getShortName())
                .engName(requestDomainDto.getEngName())
                .korName(requestDomainDto.getKorName())
                .banWord(requestDomainDto.isBanWord())
                .type(requestDomainDto.getType())
                // meaning
                .creationUser(user)
                .deleteStatus(false)
                .build();
        domainRepository.save(domain);
    }

    public void updateDomain(int domainId, RequestDomainDto requestDomainDto, User user){
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("도메인 수정 실패 : 도메인을 찾을 수 없습니다.");
                });
        domain.setShortName(requestDomainDto.getShortName());
        domain.setEngName(requestDomainDto.getEngName());
        domain.setKorName(requestDomainDto.getKorName());
        domain.setBanWord(requestDomainDto.isBanWord());
        domain.setType(requestDomainDto.getType());
        domain.setModifyUser(user);
        domainRepository.save(domain);
    }

    public void deleteDomain(int domainId, User user){
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("도메인 삭제 실패 : 도메인을 찾을 수 없습니다.");
                });
        domain.setDeleteStatus(true);
        domain.setModifyUser(user);
        domainRepository.save(domain);
    }

    public void deleteDomainDB(int domainId) {
        domainRepository.deleteById(domainId);
    }

    public void addCode(int domainId, RequestNamesDto requestNamesDto, User user) {
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("코드 추가 실패 : 도메인을 칮을 수 없습니다.");
                });
        // add code to domain
        domain.setModifyUser(user);
        domainRepository.save(domain);
        // code Repos
    }

    public void updateCode(int domainId, int codeId, RequestNamesDto requestNamesDto, User user){
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("코드 수정 실패 : 도메인을 칮을 수 없습니다.");
                });

        Code code = codeRepository.findById(codeId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("코드 수정 실패 : 코드를 찾을 수 없습니다.");
                });
        code.setShortName(requestNamesDto.getShortName());
        code.setEngName(requestNamesDto.getEngName());
        code.setKorName(requestNamesDto.getKorName());
        domain.setModifyUser(user);
        codeRepository.save(code);
        domainRepository.save(domain);
    }

    public void deleteCode(int domainId, int codeId, User user){
        Domain domain = domainRepository.findById(domainId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("코드 삭제 실패 : 도메인을 칮을 수 없습니다.");
                });

        Code code = codeRepository.findById(codeId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("코드 삭제 실패 : 코드를 찾을 수 없습니다.");
                });
        domain.setModifyUser(user);
        codeRepository.deleteById(codeId);
        domainRepository.save(domain);
    }
}
