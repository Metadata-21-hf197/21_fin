package com.example.md_back.service;

import com.example.md_back.mappers.BanWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanWordService {

    @Autowired
    private BanWordMapper banWordMapper;

    public void insert(String name){
        banWordMapper.insert(name);
    }

    public void delete(String name){
        banWordMapper.delete(name);
    }

    public String findByName(String name){
        return banWordMapper.getBanWordById(name);
    }

    public List<String> getBanWordList(){
        return banWordMapper.getBanWords();
    }

    public boolean isValid(List<String> names) {
        for(String s : names){
            if(banWordMapper.getBanWordById(s) != null){
                return false;
            }
        }
        return true;
    }
}
