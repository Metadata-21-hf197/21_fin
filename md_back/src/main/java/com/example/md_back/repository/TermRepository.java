package com.example.md_back.repository;

import com.example.md_back.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Integer> {
    List<Term> findByEngName(String engName);

    List<Term> findByShortName(String shortName);

    List<Term> findByKorName(String korName);

    @Query(value = "SELECT * FROM Term WHERE korName = ?1 OR engName = ?1 OR shortName = ?1 AND deleteStatus = FALSE", nativeQuery = true)
    List<Term> findByName(String name);

    @Query(value = "SELECT * FROM Term WHERE deleteStatus = FALSE", nativeQuery = true)
    List<Term> getTerms();

    @Query(value = "SELECT * FROM Term WHERE createUserId = :#{#userId} OR modifyUserId = :#{#userId}", nativeQuery = true)
    List<Term> findByCreateUserOrModifyUser(int userId);
}
