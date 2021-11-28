package com.example.md_back.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int approvalId;

    @Enumerated(EnumType.ORDINAL)
    private ApprovalType approvalType;

    @Enumerated(EnumType.ORDINAL)
    private ApprovalStatus approvalStatus;

    @Enumerated(EnumType.ORDINAL)
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

    @CreationTimestamp
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