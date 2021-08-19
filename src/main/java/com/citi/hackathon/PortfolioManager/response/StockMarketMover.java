package com.citi.hackathon.PortfolioManager.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockMarketMover {
    private String stockName;
    private Float marketChange;
}
