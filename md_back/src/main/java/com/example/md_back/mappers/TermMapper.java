package com.example.md_back.mappers;

import com.example.md_back.model.Term;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TermMapper {
    void insertTerm(Term term);
    void updateTerm(Term term);
    void deleteTerm(Term term);
    List<Term> getTerms();
    List<Term> getTermsByName(String name);
    Term getTermById(int id);
    List<Term> getTermsByUserId(int userId);
    void deleteTermDB(int id);
}
