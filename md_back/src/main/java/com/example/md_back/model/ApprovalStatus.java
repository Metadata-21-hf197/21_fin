package com.example.md_back.model;

public enum ApprovalStatus{
    Pending(1), Confirm(2), Denied(3);

    private int value;

    public int getValue(){
        return value;
    }

    ApprovalStatus(int value) {
        this.value = value;
    }

    public static ApprovalStatus valueOf(int value){
        switch (value) {
            case 1 : return ApprovalStatus.Pending;
            case 2 : return ApprovalStatus.Confirm;
            case 3 : return ApprovalStatus.Denied;
            default: throw new AssertionError("Approval Status Error : Value Not Defined");
        }
    }
}
