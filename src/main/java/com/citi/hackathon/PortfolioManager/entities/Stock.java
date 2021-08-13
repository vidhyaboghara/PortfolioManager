package com.citi.hackathon.PortfolioManager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="stock")
@Getter
@Setter
public class Stock implements Serializable {
    @EmbeddedId
    private StockIdentifier stockIdentifier;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "close_price")
    private Float closePrice;


}
