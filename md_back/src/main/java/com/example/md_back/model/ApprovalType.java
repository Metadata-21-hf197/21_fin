package com.example.md_back.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public enum ApprovalType {
    CREATE(1), UPDATE(2), DELETE(3);

    @Getter
    @Setter
    private int code;

    ApprovalType(int code){
        this.code = code;
    }

}
