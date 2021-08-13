package com.citi.hackathon.PortfolioManager.repositories;

import com.citi.hackathon.PortfolioManager.entities.Transaction;
import com.citi.hackathon.PortfolioManager.entities.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Collection<Transaction> getByUserId(Integer userId);

    Collection<Transaction> getByTransactionTypeAndStockId(TransactionType t, int id);
}
