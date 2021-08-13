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
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, length = 100)
    private String shortName;

    @Column(nullable = false, length = 100)
    private String engName;

    @Column(nullable = true, length = 100)
    private String korName;

    @Column(nullable = false)
    private int index;
    // 필요한 지 모르겠음
    // 처리만 어려움

    @Column(nullable = false)
    private boolean banWord;
}
