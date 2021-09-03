package com.example.md_back.dto;

import lombok.Getter;

@Getter
public class RequestDomainDto {
    private String shortName;
    private String engName;
    private String korName;
    private String meaning;
    private String type;
    private boolean banWord;
}
