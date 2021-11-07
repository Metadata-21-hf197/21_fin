package com.example.md_back.model;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum WordType{
    WORD(1), TERM(2), DOMAIN(3), CODE(4);

    @Getter
    
    private int value;

    WordType(int value) {
        this.value = value;
    }

    public static WordType valueOf(int value){
        switch (value) {
            case 1 : return WordType.WORD;
            case 2 : return WordType.TERM;
            case 3 : return WordType.DOMAIN;
            case 4 : return WordType.CODE;
            default: throw new AssertionError("Word Type Error : Value Not Defined");
        }
    }

}
