package com.example.md_back.mappers;

import com.example.md_back.model.Term;

import java.util.List;

public interface TermMapper {
    public List<Term> getTermListByUserId(int userId);
}
