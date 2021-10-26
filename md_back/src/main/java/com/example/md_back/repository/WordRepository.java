package com.example.md_back.repository;

import com.example.md_back.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Integer> {
    List<Word> findByEngName(String engName);

    List<Word> findByKorName(String korName);

    List<Word> findByShortName(String shortName);

    @Query(value = "SELECT * FROM Word WHERE korName = name OR engName = name OR shortName = name AND deleteStatus = FALSE", nativeQuery = true)
    List<Word> findByName(String name);

    @Query(value = "SELECT * FROM Word WHERE deleteStatus = FALSE", nativeQuery = true)
    List<Word> getWords();
}
