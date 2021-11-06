package com.example.md_back.model;

import lombok.Getter;

public enum ApprovalType {
    CREATE(1), UPDATE(2), DELETE(3);

    @Getter
    private int code;

    ApprovalType(int code){
        this.code = code;
    }

}
