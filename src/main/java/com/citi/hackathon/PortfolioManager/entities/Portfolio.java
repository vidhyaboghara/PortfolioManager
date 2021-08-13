package com.citi.hackathon.PortfolioManager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="portfolio")
@Getter
@Setter
public class Portfolio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "current_value")
    private Float currentValue;

    @Column(name = "user_id")
    private Integer userId;

    @JoinColumn(name = "id", referencedColumnName = "user_id")
    @ManyToOne( cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private User user;
}
