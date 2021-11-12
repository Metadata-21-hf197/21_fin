package com.example.md_back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String shortName;

    @Column(nullable = false, length = 100)
    private String engName;

    @Column(length = 100)
    private String korName;

    @ManyToOne
    @JoinColumn(name="domainId", nullable = false)
    private Domain domain;

    public void approvalToCode(Approval approval) {
        id = approval.getTargetId();
        if (approval.getSlaveId() > 0) { // id != null and id !=0
            if (approval.getShortName() != null)
                shortName = approval.getShortName();
            if (approval.getEngName() != null)
                engName = approval.getEngName();
            if (approval.getKorName() != null)
                korName = approval.getKorName();
            // domain update Code
        }
    }
}