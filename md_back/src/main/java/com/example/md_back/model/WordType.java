package com.example.md_back.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public enum WordType{
    WORD(1), TERM(2), DOMAIN(3), CODE(4);

    @Getter
    @Setter
    private int code;

    WordType(int code) {
        this.code = code;
    }
}
