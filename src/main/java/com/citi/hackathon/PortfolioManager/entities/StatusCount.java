package com.citi.hackathon.PortfolioManager.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusCount {
    private Integer stockId;

    public StatusCount(Integer stockId, Long count) {
        this.stockId = stockId;
        this.count = count;
    }

    private Long count;
}
