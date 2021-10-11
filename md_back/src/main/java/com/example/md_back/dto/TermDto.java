package com.example.md_back.dto;

import com.example.md_back.model.Approval;
import com.example.md_back.model.WordType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TermDto extends WordDto {
    private List<Integer> words;

    @Override
    public Approval dtoToApproval() {
        Approval approval = super.dtoToApproval();
        approval.setWordType(WordType.TERM);
        return approval;
    }

    public List<Approval> termToApprovals(){
        Approval approval = new Approval();
        List<Approval> approvals = new ArrayList<>();
        for (int i: words){
            approval.setSlaveId(i);
            approvals.add(approval);
        }
        return approvals;
    }
}
