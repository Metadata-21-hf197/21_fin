package com.example.md_back.mappers;

import com.example.md_back.model.Approval;
import com.example.md_back.model.Domain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DomainMapper {
    void insertDomain(Domain domain);
    void updateDomain(Domain domain);
    void updateDomainByCode(Approval approval);
    void deleteDomain(Domain domain);
    List<Domain> getDomains();
    List<Domain> getDomainsByName(String name);
    Domain getDomainById(int id);
    List<Domain> getDomainsByUserId(int userId);
    void deleteDomainDB(int id);

}
