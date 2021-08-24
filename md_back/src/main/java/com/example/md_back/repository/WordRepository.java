package com.example.md_back.repository;

import com.example.md_back.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Integer> {
    List<Word> findByEngName(String engName);
    List<Word> findByKorName(String korName);

}
