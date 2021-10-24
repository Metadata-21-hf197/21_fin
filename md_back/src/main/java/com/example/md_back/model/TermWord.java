package com.example.md_back.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class TermWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="termId", nullable = false)
    private Term term;

    @ManyToOne
    @JoinColumn(name = "wordId", nullable = false)
    private Word word;
}
