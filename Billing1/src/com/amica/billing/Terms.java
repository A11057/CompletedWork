package com.amica.billing;

import lombok.Getter;

public enum Terms {
    CASH(0),
    CREDIT_30(30),
    CREDIT_45(45),
    CREDIT_60(60),
    CREDIT_90(90);

    @Getter
    private int days;

    private Terms (int days) {
        this.days = days;
    }
}