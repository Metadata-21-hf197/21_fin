package com.example.md_back.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(nullable = true, length = 100)
    private String shortName;

    @Column(nullable = false, length = 100)
    private String engName;

    @Column(nullable = true, length = 100)
    private String korName;

    @Column(nullable = false)
    private boolean banWord;

    @Column(nullable = false)
    private boolean deleteStatus;

    @ManyToOne
    @JoinColumn(name="crateUserId")
    private User creationUser;

    @CreationTimestamp
    private Timestamp creationDate;

    @ManyToOne
    @JoinColumn(name="updateUserId")
    private User modifyUser;

    @CreationTimestamp
    private Timestamp modifyDate;
}
