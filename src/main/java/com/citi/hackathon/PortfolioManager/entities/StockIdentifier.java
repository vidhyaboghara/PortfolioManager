package com.citi.hackathon.PortfolioManager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class StockIdentifier implements Serializable {
    @Column(name = "stock_id")
    private Integer stockId;

    @Column(name = "date")
    private Date date;
}
