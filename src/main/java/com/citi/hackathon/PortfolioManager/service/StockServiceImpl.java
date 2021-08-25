package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Stock;
import com.citi.hackathon.PortfolioManager.entities.StockIdentifier;
import com.citi.hackathon.PortfolioManager.repositories.StockRepository;
import com.citi.hackathon.PortfolioManager.repositories.TransactionRepository;
import com.citi.hackathon.PortfolioManager.response.StockMarketMover;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public List<String> getAllStockNames() {
        return stockRepository.findAllStockNames();
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

    @Override
    public List<StockMarketMover> getMarketMovers() throws ParseException {
        List<StockMarketMover> marketMovers = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Stock> stocksToday = stockRepository.findByStockIdentifier_Date(dateFormat.parse(dateFormat.format(new Date())));
        for (Stock s: stocksToday) {
            Stock yesterdayStock = stockRepository.findByStockIdentifier_StockIdAndStockIdentifier_Date(s.getStockIdentifier().getStockId(),dateFormat.parse(dateFormat.format(yesterday())));
            StockMarketMover marketMover = new StockMarketMover();
            marketMover.setStockName(s.getStockName());
            marketMover.setStockPrice(s.getClosePrice());
            DecimalFormat df = new DecimalFormat("0.000");
            marketMover.setMarketChange(Float.valueOf(df.format((s.getClosePrice() - yesterdayStock.getClosePrice())/100)));
            marketMovers.add(marketMover);
        }
        return marketMovers;
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
