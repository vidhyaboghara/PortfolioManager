package com.citi.hackathon.PortfolioManager.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummarizedAmount {
    private Double amount;

    public SummarizedAmount(Double amount) {
        this.amount = amount;
    }
}
