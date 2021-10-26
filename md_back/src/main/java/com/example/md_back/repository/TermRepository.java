package com.example.md_back.repository;

import com.example.md_back.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Integer> {
    List<Term> findByEngName(String engName);

    List<Term> findByShortName(String shortName);

    List<Term> findByKorName(String korName);

    @Query(value = "SELECT * FROM Term WHERE korName = name OR engName = name OR shortName = name AND deleteStatus = FALSE", nativeQuery = true)
    List<Term> findByName(String name);

    @Query(value = "SELECT * FROM Term WHERE deleteStatus = FALSE", nativeQuery = true)
    List<Term> getTerms();
}
