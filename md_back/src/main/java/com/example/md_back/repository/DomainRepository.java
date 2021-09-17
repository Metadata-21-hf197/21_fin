package com.example.md_back.repository;

import com.example.md_back.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Integer> {
    List<Domain> findByEngName(String engName);
    List<Domain> findByShortName(String shortName);
    List<Domain> findByKorName(String korName);

    @Query(value="SELECT * FROM Domain WHERE banWord = FALSE", nativeQuery = true)
    List<Domain> getTrueDomains();
}
