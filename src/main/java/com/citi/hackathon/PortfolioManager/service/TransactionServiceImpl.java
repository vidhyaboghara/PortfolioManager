package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.RequestTransaction;
import com.citi.hackathon.PortfolioManager.entities.Stock;
import com.citi.hackathon.PortfolioManager.entities.Transaction;
import com.citi.hackathon.PortfolioManager.entities.TransactionType;
import com.citi.hackathon.PortfolioManager.repositories.StockRepository;
import com.citi.hackathon.PortfolioManager.repositories.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StockRepository stockRepository;


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
    public void addNewTransaction(RequestTransaction transaction) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Stock s = stockRepository.getByStockName(transaction.getStockName()).get(1);
        Stock st = stockRepository.findByStockIdentifier_StockIdAndStockIdentifier_Date(s.getStockIdentifier().getStockId(),dateFormat.parse(dateFormat.format(new Date())) );
        Transaction tr = new Transaction();
        tr.setTransactionDate(transaction.getTransactionDate());
        tr.setTransactionType(transaction.getTransactionType());
        tr.setAmount(transaction.getAmount());
        tr.setUserId(transaction.getUserId());
        tr.setStockId(st.getStockIdentifier().getStockId());
        tr.setStock(st);

        if(tr.getTransactionType().equals(TransactionType.sell)){
            int buyStocks = transactionRepository.getByTransactionTypeAndStockId(TransactionType.buy, tr.getStockId()).size();
            int sellStocks = transactionRepository.getByTransactionTypeAndStockId(TransactionType.sell, tr.getStockId()).size();

            if(buyStocks-sellStocks >= tr.getAmount() && !tr.getTransactionDate().after(new Date())){
                transactionRepository.save(tr);
            }
            else {
                //todo handle exception
            }
        }
        else{
            transactionRepository.save(tr);
        }
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
