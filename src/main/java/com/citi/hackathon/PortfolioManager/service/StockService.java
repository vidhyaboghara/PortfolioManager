package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Stock;

import java.util.Collection;

public interface StockService {
    Collection<Stock> getAll();

    Stock getStockById(Integer id);

    Stock getStockByName(String name);

    void addStock(Stock s);

    void updateStock(Stock stock);

    void deleteStock(int id);
}
