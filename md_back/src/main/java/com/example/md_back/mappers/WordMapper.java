package com.example.md_back.mappers;

import com.example.md_back.dto.WordDto;
import com.example.md_back.model.Word;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WordMapper {
    public List<Word> getWordListByUserId(int id);
}
