package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Stock;
import com.citi.hackathon.PortfolioManager.entities.StockIdentifier;
import com.citi.hackathon.PortfolioManager.repositories.StockRepository;
import com.citi.hackathon.PortfolioManager.repositories.TransactionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
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
    public List<Stock> getStockById(Integer id){
        return stockRepository.findByStockIdentifier_StockId(id);
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
        Optional<Stock> stockInDb = stockRepository.findById(stock.getStockIdentifier());
        if(stockInDb.isPresent()){
            stockRepository.save(stock);
        }
    }

    @Override
    public void deleteStock(int id, Date date){
        Stock stock = stockRepository.findByStockIdentifier_StockIdAndStockIdentifier_Date(id, date);
        if(null != stock){
            stockRepository.delete(stock);
        }
    }
}
