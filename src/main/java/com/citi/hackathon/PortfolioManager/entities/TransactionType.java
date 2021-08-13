package com.citi.hackathon.PortfolioManager.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {
    buy ("buy"),  //calls constructor with value 2
    sell ("sell")   //calls constructor with value 1
    ; // semicolon needed when fields / methods follow

    private final String typeCode;

    TransactionType(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    @Override
    @JsonValue
    public String toString(){
        return String.valueOf(typeCode);
    }

    @JsonCreator
    public static TransactionType fromValue(String text){
        for(TransactionType t : TransactionType.values()){
            if(String.valueOf(t.typeCode).equals(text)){
                return t;
            }
        }
        return null;
    }
}
