package com.example.md_back.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public enum ApprovalStatus {
    Pending(1), Confirm(2), Denied(3);

    @Getter
    @Setter
    private int code;

    ApprovalStatus(int code) {
        this.code = code;
    }
}
