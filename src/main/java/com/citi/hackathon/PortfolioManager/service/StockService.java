package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Stock;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface StockService {
    Collection<Stock> getAll();

    List<Stock> getStockById(Integer id);

    Stock getStockByName(String name);

    void addStock(Stock s);

    void updateStock(Stock stock);

    void deleteStock(int id, Date date);
}
