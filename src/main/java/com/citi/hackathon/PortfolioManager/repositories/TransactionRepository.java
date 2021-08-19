package com.citi.hackathon.PortfolioManager.repositories;

import com.citi.hackathon.PortfolioManager.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Collection<Transaction> getByUserId(Integer userId);

    Collection<Transaction> getByTransactionTypeAndStockId(TransactionType t, int id);

    @Query("select new com.citi.hackathon.PortfolioManager.entities.StatusCount(tr.stockId,SUM(tr.amount)) from Transaction tr WHERE tr.userId = :userId and tr.transactionType = 'buy' group by tr.stockId")
    Collection<StatusCount> getBuyCount(@Param("userId") Integer userId);

    @Query("select new com.citi.hackathon.PortfolioManager.entities.StatusCount(tr.stockId,SUM(tr.amount)) from Transaction tr WHERE tr.userId = :userId and tr.transactionType = 'sell' group by tr.stockId")
    Collection<StatusCount> getSellCount(@Param("userId") Integer userId);

    @Query("select new com.citi.hackathon.PortfolioManager.entities.SummarizedAmount(SUM(tr.amount*st.closePrice)) from Transaction tr join Stock st on tr.stockId = st.stockIdentifier.stockId and tr.transactionDate = st.stockIdentifier.date where tr.transactionType = 'buy' and tr.userId = :userId and DATEDIFF(:tdate,tr.transactionDate) between 0 and 30")
    SummarizedAmount getBuyAmount(@Param("tdate") Date date, @Param("userId") Integer userId);

    @Query("select new com.citi.hackathon.PortfolioManager.entities.SummarizedAmount(SUM(tr.amount*st.closePrice)) from Transaction tr join Stock st on tr.stockId = st.stockIdentifier.stockId and tr.transactionDate = st.stockIdentifier.date where tr.transactionType = 'sell' and tr.userId = :userId and DATEDIFF(:tdate,tr.transactionDate) between 0 and 30")
    SummarizedAmount getSellAmount(@Param("tdate") Date date, @Param("userId") Integer userId);

    @Query("select new com.citi.hackathon.PortfolioManager.entities.StockTransactions(st.stockIdentifier.stockId, st.stockName, tr.amount, " +
            "st.closePrice, tr.transactionDate) from Transaction tr join Stock st on tr.stockId = st.stockIdentifier.stockId and tr.transactionDate = st.stockIdentifier.date where " +
            "tr.transactionType = 'buy' and tr.userId = :userId and DATEDIFF(:tdate,tr.transactionDate) between 0 and 30 order by tr.transactionDate asc")
    List<StockTransactions> getBuyTransactions(@Param("tdate") Date date, @Param("userId") Integer userId);

    @Query("select new com.citi.hackathon.PortfolioManager.entities.StockTransactions(st.stockIdentifier.stockId, st.stockName, tr.amount, " +
            "st.closePrice, tr.transactionDate) from Transaction tr join Stock st on tr.stockId = st.stockIdentifier.stockId and tr.transactionDate = st.stockIdentifier.date where " +
            "tr.transactionType = 'sell' and tr.userId = :userId and DATEDIFF(:tdate,tr.transactionDate) between 0 and 30 order by tr.transactionDate asc")
    List<StockTransactions> getSellTransactions(@Param("tdate") Date date, @Param("userId") Integer userId);
}
