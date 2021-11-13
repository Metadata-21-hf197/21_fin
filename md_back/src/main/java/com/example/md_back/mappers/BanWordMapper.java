package com.example.md_back.mappers;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BanWordMapper {
    void insert(String name);
    void delete(String name);
    String getBanWordById(String name);
    List<String> getBanWords();
}
