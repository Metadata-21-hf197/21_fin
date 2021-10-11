package com.example.md_back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, length = 100)
    private String shortName;

    @Column(nullable = false, length = 100)
    private String engName;

    @Column(nullable = true, length = 100)
    private String korName;

    @Lob
    private String meaning;

    @OneToMany(mappedBy = "domain")
    @JsonIgnoreProperties({"domain"})
    private List<Code> codes;

    @Column(nullable = false)
    private boolean deleteStatus;

    @ManyToOne
    @JoinColumn(name="createUserId", nullable = false)
    private User createUser;

    @CreationTimestamp
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name="modifyUserId")
    private User modifyUser;

    @UpdateTimestamp
    private Timestamp modifyDate;

    public void approvalToDomain(Approval approval) {
        if (approval.getShortName() != null)
            shortName = approval.getShortName();
        if (approval.getEngName() != null)
            engName = approval.getEngName();
        if (approval.getKorName() != null)
            korName = approval.getKorName();
        if (approval.getMeaning() != null)
            meaning = approval.getMeaning();
        if (approval.getApprovalType() == ApprovalType.DELETE) {
            deleteStatus = true;
        } else if (approval.getApprovalType() == ApprovalType.CREATE) {
            createUser = approval.getCreateUser();
            createDate = approval.getCreateDate();
            deleteStatus = false;
        } else {
            modifyUser = approval.getCreateUser();
            modifyDate = approval.getCreateDate();
        }
    }

}
