package com.example.md_back.mappers;

import com.example.md_back.model.Word;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WordMapper {
    void insertWord(Word word);
    void updateWord(Word word);
    void deleteWord(Word word);
    List<Word> getWords();
    List<Word> getWordsByName(String name);
    Word getWordById(int id);
    List<Word> getWordListByUserId(int userId);
    void deleteWordDB(int id);
}
