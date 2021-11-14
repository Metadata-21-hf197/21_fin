package com.example.md_back.service;

import com.example.md_back.mappers.BanWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanWordService {

    @Autowired
    private BanWordMapper banWordMapper;

    public void insert(String name) {
        if (name == null || name.length() <= 0) throw new IllegalArgumentException("금지어 추가 실패 : 금지어를 입력하세요");
        banWordMapper.insert(name);
    }

    public void delete(String name) {
        if (name == null || name.length() <= 0) throw new IllegalArgumentException("금지어 삭제 실패 : 금지어를 입력하세요");
        banWordMapper.delete(name);
    }

    public String findByName(String name) {
        if (name == null || name.length() <= 0) throw new IllegalArgumentException("금지어 검색 실패 : 금지어를 입력하세요");
        return banWordMapper.getBanWordById(name);
    }

    public List<String> getBanWordList() {
        return banWordMapper.getBanWords();
    }

    public boolean isValid(List<String> names) {
        for (String s : names) {
            if (s == null || s.length() <= 0) throw new IllegalArgumentException("금지어 확인 실패 : 단어를 입력하세요");
            if (banWordMapper.getBanWordById(s) != null) {
                return false;
            }
        }
        return true;
    }
}
