package com.example.md_back.service;

import com.example.md_back.model.BanWord;
import com.example.md_back.repository.BanWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanWordService {

    @Autowired
    private BanWordRepository banWordRepository;

    public void insert(String name){
        banWordRepository.save(new BanWord(name));
    }

    public void delete(String name){
        banWordRepository.deleteByName(name);
    }

    public BanWord findByName(String name){
        return banWordRepository.findByName(name);
    }

    public List<BanWord> getBanWordList(){
        return banWordRepository.findAll();
    }

    public boolean isValid(List<String> names) {
        for(String s : names){
            if(banWordRepository.findByName(s) != null){
                return false;
            }
        }
        return true;
    }
}
