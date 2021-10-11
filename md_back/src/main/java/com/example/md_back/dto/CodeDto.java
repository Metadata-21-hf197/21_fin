package com.example.md_back.dto;

import com.example.md_back.model.Approval;
import com.example.md_back.model.WordType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeDto {
    private String shortName;
    private String engName;
    private String korName;

    public Approval dtoToApproval(){
        Approval approval = new Approval();
        approval.setWordType(WordType.CODE);
        approval.setShortName(shortName);
        approval.setEngName(engName);
        approval.setKorName(korName);
        return approval;
    }
}
