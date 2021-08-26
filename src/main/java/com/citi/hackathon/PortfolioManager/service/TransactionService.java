package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.RequestTransaction;
import com.citi.hackathon.PortfolioManager.entities.Transaction;

import java.text.ParseException;
import java.util.Collection;

public interface TransactionService {
    Collection<Transaction> getAll();

    Transaction getTransactionById(Integer id);

    Collection<Transaction> getTransactionsByUserId(Integer userId);

    void addNewTransaction(RequestTransaction transaction) throws ParseException;

    void updateTransaction(Transaction transaction);

    void deleteTransaction(int id);
}
