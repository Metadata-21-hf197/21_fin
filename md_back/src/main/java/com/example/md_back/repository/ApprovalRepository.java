package com.example.md_back.repository;

import com.example.md_back.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, Integer> {
    @Query(value = "SELECT * FROM Approval WHERE createUserId = :#{#userId}", nativeQuery = true)
    List<Approval> findByCreateUser(int userId);

    @Query(value = "SELECT * FROM Approval WHERE confirmUser = ?1", nativeQuery = true)
    List<Approval> findByConfirmUser(int userId);
}