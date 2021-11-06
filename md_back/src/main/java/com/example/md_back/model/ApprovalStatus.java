package com.example.md_back.model;

import lombok.Getter;

public enum ApprovalStatus {
    Pending(1), Confirm(2), Denied(3);

    @Getter
    private int code;

    ApprovalStatus(int code) {
        this.code = code;
    }
}
