package com.example.md_back.mappers;

import com.example.md_back.model.Code;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodeMapper {
    void insertCode(Code code);
    void updateCode(Code code);
    void deleteCode(int id);
    void deleteCodeByDomainId(int domainId);
    List<Code> getCodes();
    List<Code> getCodesByName(String name);
    Code getCodeById(int id);
    List<Code> getCodesByDomainId(int domainId);
}
