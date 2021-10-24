package com.example.md_back.dto;

import com.example.md_back.model.Approval;
import com.example.md_back.model.WordType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordDto {
    private String shortName;
    private String engName;
    private String korName;
    private String meaning;

    public Approval dtoToApproval(){
        Approval approval = new Approval();
        approval.setWordType(WordType.WORD);
        approval.setShortName(shortName);
        approval.setEngName(engName);
        approval.setKorName(korName);
        approval.setMeaning(meaning);
        return approval;
    }
}
