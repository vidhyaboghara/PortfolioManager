package com.citi.hackathon.PortfolioManager.repositories;

import com.citi.hackathon.PortfolioManager.entities.Stock;
import com.citi.hackathon.PortfolioManager.entities.StockIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface StockRepository extends JpaRepository<Stock, StockIdentifier> {
    List<Stock> findByStockIdentifier_StockId(int id);

    Stock findByStockIdentifier_StockIdAndStockIdentifier_Date(int id, Date date);

    List<Stock> findByStockIdentifier_Date(Date date);

    Stock getByStockName(String stockName);
}
