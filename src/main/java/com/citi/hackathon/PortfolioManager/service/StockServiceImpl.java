package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Stock;
import com.citi.hackathon.PortfolioManager.entities.Transaction;
import com.citi.hackathon.PortfolioManager.repositories.StockRepository;
import com.citi.hackathon.PortfolioManager.repositories.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;

    private static final Logger logger = LogManager.getLogger(TransactionRepository.class);

    @Override
    public Collection<Stock> getAll(){
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Integer id){
        return stockRepository.getById(id);
    }

    @Override
    public Stock getStockByName(String name){
        return stockRepository.getByStockName(name);
    }

    @Override
    public void addStock(Stock s){
        stockRepository.save(s);
    }

    @Override
    public void updateStock(Stock stock){
        Optional<Stock> stockInDb = stockRepository.findById(stock.getStockId());
        if(stockInDb.isPresent()){
            stockRepository.save(stock);
        }
    }

    @Override
    public void deleteStock(int id){
        Optional<Stock> stock = stockRepository.findById(id);
        if(stock.isPresent()){
            stockRepository.delete(stock.get());
        }
    }
}
