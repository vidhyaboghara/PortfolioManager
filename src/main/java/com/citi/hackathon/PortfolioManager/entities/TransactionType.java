package com.citi.hackathon.PortfolioManager.entities;

public enum TransactionType {
    BUY (2),  //calls constructor with value 2
    SELL (1)   //calls constructor with value 1
    ; // semicolon needed when fields / methods follow

    private final int typeCode;

    TransactionType(int typeCode) {
        this.typeCode = typeCode;
    }

    public int getTypeCode() {
        return this.typeCode;
    }
}
