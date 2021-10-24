package com.example.md_back.dto;

import com.example.md_back.model.Approval;
import com.example.md_back.model.WordType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DomainDto extends WordDto {
    private List<CodeDto> codes;

    public List<Approval> domainToApproval(){
        List<Approval> approvals = new ArrayList<>();
        Approval approval = super.dtoToApproval();
        approval.setWordType(WordType.DOMAIN);
        approvals.add(approval);
        for (CodeDto codeDto : codes)
            approvals.add(codeDto.dtoToApproval());
        return approvals;
    }

    @Override
    public Approval dtoToApproval() {
        Approval approval = super.dtoToApproval();
        approval.setWordType(WordType.DOMAIN);
        return approval;
    }

    public List<Approval> codesToApproval(){
        List<Approval> approvals = new ArrayList<>();
        for (CodeDto codeDto : codes)
            approvals.add(codeDto.dtoToApproval());
        return approvals;
    }
}

