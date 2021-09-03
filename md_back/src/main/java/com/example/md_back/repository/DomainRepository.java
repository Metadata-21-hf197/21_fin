package com.example.md_back.repository;

import com.example.md_back.model.Domain;
import com.example.md_back.model.Term;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Integer> {
    List<Domain> findByEngName(String engName);
    List<Domain> findByShortName(String shortName);
    List<Domain> findByKorName(String korName);
}
