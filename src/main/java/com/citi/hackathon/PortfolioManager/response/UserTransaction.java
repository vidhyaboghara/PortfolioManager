package com.citi.hackathon.PortfolioManager.response;

import com.citi.hackathon.PortfolioManager.entities.Transaction;
import com.citi.hackathon.PortfolioManager.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserTransaction extends User {
    private List<Transaction> transactionList;
}
