package com.example.md_back.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TermDto extends WordDto {
    private List<Integer> words;
}
