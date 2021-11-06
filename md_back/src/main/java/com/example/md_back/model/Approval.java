package com.example.md_back.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int approvalId;

    @Column(nullable = false)
    private ApprovalType approvalType;

    @Column(nullable = false)
    private ApprovalStatus approvalStatus;

    @Column(nullable = false)
    private WordType wordType;

    @Column(nullable = false)
    private int targetId;

    @Column
    private int slaveId;

    @Column
    private String shortName;

    @Column
    private String engName;

    @Column
    private String korName;

    @Lob
    private String meaning;

    @ManyToOne
    @JoinColumn(name = "createUserId", nullable = false)
    private User createUser;

    @Column(nullable = false)
    private Timestamp createDate;

    @ManyToOne
    @JoinColumn(name = "confirmUserId")
    private User confirmUser;

    @Column
    private Timestamp confirmDate;

    public boolean isConfirmed() {
        return approvalStatus != ApprovalStatus.Pending;
    }
}