package com.example.md_back.repository;

import com.example.md_back.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Integer> {
    List<Term> findByEngName(String engName);
    List<Term> findByShortName(String shortName);
    List<Term> findByKorName(String korName);

    @Query(value = "SELECT * FROM Term WHERE banWord = FALSE", nativeQuery = true)
    List<Term> getTrueTerms();
}
