package com.example.md_back.model;

import com.example.md_back.dto.WordDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class WordApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diffId;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp date;

    @Column(nullable = false)
    private ApprovalType approvalType;

    @Column
    private boolean deleteStatus;

    @Column
    private int wordId;

    @Column
    private String shortName;

    @Column
    private String engName;

    @Column
    private String korName;

    @Lob
    private String meaning;

    public WordApproval(WordDto wordDto) {
        shortName = wordDto.getShortName();
        korName = wordDto.getKorName();
        engName = wordDto.getEngName();
        meaning = wordDto.getMeaning();
        deleteStatus = false;
        approvalType = ApprovalType.INSERT;
    }

    public WordApproval(WordDto wordDto, int wordId) {
        this.wordId = wordId;
        shortName = wordDto.getShortName();
        korName = wordDto.getKorName();
        engName = wordDto.getEngName();
        meaning = wordDto.getMeaning();
        deleteStatus = false;
        approvalType = ApprovalType.UPDATE;
    }

    public WordApproval(int wordId){
        this.wordId = wordId;
        deleteStatus = true;
        approvalType = ApprovalType.DELETE;
    }
}
