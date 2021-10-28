package com.example.md_back.repository;

import com.example.md_back.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Integer> {
    List<Domain> findByEngName(String engName);

    List<Domain> findByShortName(String shortName);

    List<Domain> findByKorName(String korName);

    @Query(value = "SELECT * FROM Domain WHERE korName = ?1 OR engName = ?1 OR shortName = ?1 AND deleteStatus = FALSE", nativeQuery = true)
    List<Domain> findByName(String name);

    @Query(value = "SELECT * FROM Domain WHERE deleteStatus = FALSE", nativeQuery = true)
    List<Domain> getDomains();

    @Query(value = "SELECT * FROM Domain WHERE createUser = ?1 OR modifyUser = ?1", nativeQuery = true)
    List<Domain> findByCreateUserOrModifyUser(int userId);

}
