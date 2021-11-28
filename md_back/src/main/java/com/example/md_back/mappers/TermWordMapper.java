package com.example.md_back.mappers;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TermWordMapper {
    void insert(int termId, int wordId);
    void delete(int id);
    void deleteByTermId(int termId);
    List<Integer> getWordIdListByTermId(int termId);
}
