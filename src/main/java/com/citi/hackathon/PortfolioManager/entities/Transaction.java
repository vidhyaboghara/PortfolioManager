package com.citi.hackathon.PortfolioManager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="transaction")
@Getter
@Setter
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "user_id")
    private Integer userId;

    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false,updatable = false)
    @ManyToOne( cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private User user;

    @Column(name = "stock_id")
    private Integer stockId;

    @JoinColumns(
            {
                    @JoinColumn(name = "stock_id", referencedColumnName = "stock_id", insertable = false, updatable = false),
                    @JoinColumn(name = "transaction_date", referencedColumnName = "date", insertable = false, updatable = false)
            }
    )
    @OneToOne( cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private Stock stock;
}
