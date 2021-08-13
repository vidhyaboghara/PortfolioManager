package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Transaction;
import com.citi.hackathon.PortfolioManager.repositories.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    private static final Logger logger = LogManager.getLogger(TransactionRepository.class);

    @Override
    public Collection<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(Integer id){
        return transactionRepository.getById(id);
    }

    @Override
    public Collection<Transaction> getTransactionsByUserId(Integer userId){
        return transactionRepository.getByUserId(userId);
    }

    @Override
    public void addNewTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    @Override
    public void updateTransaction(Transaction transaction){
        Optional<Transaction> transactionInDb = transactionRepository.findById(transaction.getTransactionId());
        if(transactionInDb.isPresent()){
            transactionRepository.save(transaction);
        }
    }

    @Override
    public void deleteTransaction(int id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isPresent()){
            transactionRepository.delete(transaction.get());
        }
    }
}
