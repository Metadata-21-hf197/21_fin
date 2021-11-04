package com.example.md_back.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class BanWord {

    @Id
    private String name;

    public String getName() {
        return name;
    }

    public BanWord(String name) {
        this.name = name;
    }
}
