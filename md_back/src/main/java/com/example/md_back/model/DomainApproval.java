package com.example.md_back.model;

import com.example.md_back.dto.DomainDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class DomainApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diffId;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp date;

    @Column(nullable = false)
    private ApprovalType approvalType;

    @Column
    private int domainId;

    @Column
    private String shortName;

    @Column
    private String engName;

    @Column
    private String korName;

    @Column
    private String meaning;

    @Column
    private String type;

    @OneToMany(mappedBy = "domainApproval")
    private List<CodeApproval> codes;

    @Column
    private boolean deleteStatus;

    public DomainApproval(DomainDto domainDto) {
        shortName = domainDto.getShortName();
        korName = domainDto.getKorName();
        engName = domainDto.getEngName();
        meaning = domainDto.getMeaning();
        type = domainDto.getType();
        codes = domainDto.getCodes();
        deleteStatus = false;
        approvalType = ApprovalType.INSERT;
    }

    public DomainApproval(DomainDto domainDto, int domainId) {
        this.domainId = domainId;
        shortName = domainDto.getShortName();
        korName = domainDto.getKorName();
        engName = domainDto.getEngName();
        meaning = domainDto.getMeaning();
        codes = domainDto.getCodes();
        deleteStatus = false;
        approvalType = ApprovalType.UPDATE;
    }

    public DomainApproval(int domainId){
        this.domainId = domainId;
        deleteStatus = true;
        approvalType = ApprovalType.DELETE;
    }

}
