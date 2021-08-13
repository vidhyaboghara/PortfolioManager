package com.citi.hackathon.PortfolioManager.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stock {
    private Integer stockId;
    private String stockName;
    private Integer amount;
}
