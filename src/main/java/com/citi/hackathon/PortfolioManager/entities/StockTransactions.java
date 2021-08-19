package com.citi.hackathon.PortfolioManager.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StockTransactions {
    private Integer id;
    private String name;
    private Integer amount;
    private Float price;
    private Date date;

    public StockTransactions(Integer id, String name, Integer amount, Float price, Date date) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.date = date;
    }
}
