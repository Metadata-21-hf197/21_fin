package com.example.md_back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String shortName;

    @Column(nullable = false, length = 100)
    private String engName;

    @Column(length = 100)
    private String korName;

    @Lob
    private String meaning;

    @Column(nullable = false)
    private boolean deleteStatus;

    @ManyToOne
    @JoinColumn(name = "createUserId", nullable = false)
    private User createUser;

    @CreationTimestamp
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name = "modifyUserId")
    private User modifyUser;

    @Column
    private Timestamp modifyDate;

    public void approvalToWord(Approval approval) {
        id = approval.getTargetId();
        if (approval.getApprovalType() == ApprovalType.DELETE) {
            modifyUser = approval.getCreateUser();
            modifyDate = approval.getCreateDate();
            deleteStatus = true;
            return;
        } else if (approval.getApprovalType() == ApprovalType.CREATE) {
            createUser = approval.getCreateUser();
            createDate = approval.getCreateDate();
            deleteStatus = false;
        } else if (approval.getApprovalType() == ApprovalType.UPDATE) {
            modifyUser = approval.getCreateUser();
            modifyDate = approval.getCreateDate();
        }
        if (approval.getShortName() != null && approval.getShortName().length() > 0)
            shortName = approval.getShortName();
        if (approval.getEngName() != null && approval.getEngName().length() > 0)
            engName = approval.getEngName();
        if (approval.getKorName() != null && approval.getKorName().length() > 0)
            korName = approval.getKorName();
        if (approval.getMeaning() != null && approval.getMeaning().length() > 0)
            meaning = approval.getMeaning();
    }
}