package com.example.md_back.model;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public enum ApprovalType {
    CREATE(1), UPDATE(2), DELETE(3);
    private int value;

    public int getValue(){
        return value;
    }

    ApprovalType(int value){
        this.value = value;
    }

    public static ApprovalType valueOf(int value){
        switch (value) {
            case 1 : return ApprovalType.CREATE;
            case 2 : return ApprovalType.UPDATE;
            case 3 : return ApprovalType.DELETE;
            default: throw new AssertionError("ApprovalType Error : Value Not Defined");
        }
    }

}
