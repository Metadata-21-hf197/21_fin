package com.example.md_back.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @Column(nullable = false)
    private boolean banWord;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "domain", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"domain"})
    private List<Code> codes;

    @Column(nullable = false)
    private boolean deleteStatus;

    @ManyToOne
    @JoinColumn(name="crateUserId")
    private User creationUser;

    @CreationTimestamp
    private Timestamp creationDate;

    @ManyToOne
    @JoinColumn(name="modifyUserId")
    private User modifyUser;

    @CreationTimestamp
    private Timestamp modifyDate;
}
