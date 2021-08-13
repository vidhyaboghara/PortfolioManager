package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Transaction;

import java.util.Collection;

public interface TransactionService {
    Collection<Transaction> getAll();

    Transaction getTransactionById(Integer id);

    Collection<Transaction> getTransactionsByUserId(Integer userId);

    void addNewTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);

    void deleteTransaction(int id);
}
