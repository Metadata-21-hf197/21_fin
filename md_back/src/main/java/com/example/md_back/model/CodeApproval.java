package com.example.md_back.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class CodeApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // null = insert
    @Column
    private int codeId;

    @Column
    private String korName;

    @Column
    private String engName;

    @Column
    private String shortName;

    @ManyToOne
    @JoinColumn(name="domainId")
    private DomainApproval domainApproval;
}
