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

    @Column(nullable = true, length = 100)
    private String shortName;

    @Column(nullable = false, length = 100)
    private String engName;

    @Column(nullable = true, length = 100)
    private String korName;

    @ManyToOne
    @JoinColumn(name="domainId", nullable = false)
    private Domain domain;
}