package com.example.md_back.repository;

import com.example.md_back.model.BanWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanWordRepository extends JpaRepository<BanWord, Integer> {
    BanWord findByName(String name);
}
