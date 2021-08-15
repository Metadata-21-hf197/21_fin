package com.example.md_back.repository;

import com.example.md_back.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {
}
