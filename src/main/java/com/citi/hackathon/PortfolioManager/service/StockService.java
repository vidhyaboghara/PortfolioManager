package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Stock;
import com.citi.hackathon.PortfolioManager.response.StockMarketMover;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface StockService {
    List<StockMarketMover> getMarketMovers() throws ParseException;

    Collection<Stock> getAll();

    List<String> getAllStockNames();

    List<Stock> getStockById(Integer id);

    Stock getStockByName(String name);

    void addStock(Stock s);

    void updateStock(Stock stock);

    void deleteStock(int id, Date date);
}
