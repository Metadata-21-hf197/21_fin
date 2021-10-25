package com.example.md_back.repository;

import com.example.md_back.model.TermWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TermWordRepository extends JpaRepository<TermWord, Integer> {
    List<TermWord> findByTermId(int termId);

    @Query(value = "SELECT wordId FROM TermWord WHERE termId = id", nativeQuery = true)
    List<Integer> getWordIdByTermId(int id);
}
