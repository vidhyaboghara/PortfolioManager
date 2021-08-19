package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.*;
import com.citi.hackathon.PortfolioManager.repositories.StockRepository;
import com.citi.hackathon.PortfolioManager.repositories.TransactionRepository;
import com.citi.hackathon.PortfolioManager.repositories.UserRepository;
import com.citi.hackathon.PortfolioManager.response.Stock;
import com.citi.hackathon.PortfolioManager.response.UserNetWorth;
import com.citi.hackathon.PortfolioManager.response.UserSummary;
import com.citi.hackathon.PortfolioManager.response.UserTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private StockRepository stockRepository;

    private static final Logger logger = LogManager.getLogger(UserRepository.class);

    @Override
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Collection<UserTransaction> getAllUsersWithTransactions() {
        Collection<User> users = userRepository.findAll();
        Collection<UserTransaction> userTransactions = new ArrayList<>();

        for (User user:users) {
            UserTransaction userTransaction = new UserTransaction();
            userTransaction.setId(user.getId());
            userTransaction.setFirstName(user.getFirstName());
            userTransaction.setLastName(user.getLastName());
            userTransaction.setEmailId(user.getEmailId());
            userTransaction.setPhone(user.getPhone());
            userTransaction.setTransactionList((List<Transaction>) transactionRepository.getByUserId(user.getId()));
            userTransactions.add(userTransaction);
        }
        return userTransactions;
    }

    @Override
    public UserTransaction getUsersWithTransactionsById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            UserTransaction userTransaction = new UserTransaction();
            userTransaction.setId(user.get().getId());
            userTransaction.setFirstName(user.get().getFirstName());
            userTransaction.setLastName(user.get().getLastName());
            userTransaction.setEmailId(user.get().getEmailId());
            userTransaction.setPhone(user.get().getPhone());
            userTransaction.setTransactionList((List<Transaction>) transactionRepository.getByUserId(user.get().getId()));
            return userTransaction;
        }
        return null;
    }

    @Override
    public User getUserById(Integer id){
        return userRepository.getById(id);
    }

    @Override
    public User getUserByEmailId(String email){
        return  userRepository.getByEmailId(email);
    }

    @Override
    public void addNewUser(User user){
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user){
        Optional<User> userInDb = userRepository.findById(user.getId());
        if(userInDb.isPresent()){
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
        }
    }

    @Override
    public UserSummary getUserSummary(Integer id){
        List<StatusCount> buycounts = (List<StatusCount>) transactionRepository.getBuyCount(id);
        List<StatusCount> sellcounts = (List<StatusCount>) transactionRepository.getSellCount(id);
        List<Stock> stockList = new ArrayList<>();
        for(StatusCount buycount: buycounts){
            Optional<StatusCount> sellcount = sellcounts.stream().filter(statusCount -> statusCount.getStockId().equals(buycount.getStockId())).findFirst();
            if (sellcount.isPresent()){
                long rem = buycount.getCount() - sellcount.get().getCount();
                buycount.setCount(rem);
            }
            if(buycount.getCount()!=0){
                Stock stock = new Stock();
                stock.setStockId(buycount.getStockId());
                stock.setAmount(Math.toIntExact(buycount.getCount()));
                stock.setStockName(stockRepository.findByStockIdentifier_StockId(buycount.getStockId()).stream().findFirst().get().getStockName());
                stockList.add(stock);
            }
        }
        Optional<User> user = userRepository.findById(id);
        UserSummary userSummary = new UserSummary();
        userSummary.setUserId(id);
        userSummary.setFirstName(user.get().getFirstName());
        userSummary.setLastName(user.get().getLastName());
        userSummary.setEmailId(user.get().getEmailId());
        userSummary.setStocks(stockList);
        return userSummary;
    }

    @Override
    public UserNetWorth getUserNetWorth(Integer id) throws ParseException {
        UserNetWorth userNetWorth = new UserNetWorth();
        UserSummary userSummary = getUserSummary(id);
        userNetWorth.setUserId(id);
        userNetWorth.setFirstName(userSummary.getFirstName());
        userNetWorth.setLastName(userSummary.getLastName());
        userNetWorth.setEmailId(userSummary.getEmailId());
        Float networth = 0.0f;
        for (Stock s: userSummary.getStocks()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(dateFormat.parse(dateFormat.format(new Date())));
            com.citi.hackathon.PortfolioManager.entities.Stock stock = stockRepository.findByStockIdentifier_StockIdAndStockIdentifier_Date(s.getStockId(), dateFormat.parse(dateFormat.format(new Date())));
            networth += s.getAmount() * stock.getClosePrice();
        }
        userNetWorth.setNetWorth(networth);
        return userNetWorth;
    }

    @Override
    public Double getUserSpending(Integer id) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SummarizedAmount buy = transactionRepository.getBuyAmount(dateFormat.parse(dateFormat.format(new Date())), id);
        SummarizedAmount sell = transactionRepository.getSellAmount(dateFormat.parse(dateFormat.format(new Date())), id);
        return buy.getAmount()-sell.getAmount();
    }

    @Override
    public Float getUserIncome(Integer id) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<StockTransactions> buyTransactions = transactionRepository.getBuyTransactions(dateFormat.parse(dateFormat.format(new Date())), id);
        List<StockTransactions> sellTransactions = transactionRepository.getSellTransactions(dateFormat.parse(dateFormat.format(new Date())), id);
        Float netIncome = 0.0f;
        for (ListIterator<StockTransactions> it = buyTransactions.listIterator(); it.hasNext();) {
            StockTransactions t = it.next();
            StockTransactions sell = sellTransactions.stream().filter(stockTransactions -> stockTransactions.getId().equals(t.getId())).findFirst().orElse(null);
            if(null!=sell){
                Float gain = sell.getPrice()*sell.getAmount() - t.getPrice()*sell.getAmount();
                netIncome += gain;
                if(t.getAmount()-sell.getAmount() != 0){
                    t.setAmount(t.getAmount()-sell.getAmount());
                    it.set(t);
                }
                else{
                    it.remove();
                }

            }
            else{
                com.citi.hackathon.PortfolioManager.entities.Stock curr = stockRepository.findByStockIdentifier_StockIdAndStockIdentifier_Date(t.getId(), dateFormat.parse(dateFormat.format(new Date())));
                Float gain = curr.getClosePrice()*t.getAmount() - t.getPrice()*t.getAmount();
                netIncome += gain;
                it.remove();
            }
        }
        if(!buyTransactions.isEmpty()){
            for (Iterator<StockTransactions> it = buyTransactions.listIterator(); it.hasNext();) {
                StockTransactions t = it.next();
                com.citi.hackathon.PortfolioManager.entities.Stock curr = stockRepository.findByStockIdentifier_StockIdAndStockIdentifier_Date(t.getId(), dateFormat.parse(dateFormat.format(new Date())));
                Float gain = curr.getClosePrice()*t.getAmount() - t.getPrice()*t.getAmount();
                netIncome += gain;
                it.remove();
            }
        }

        return netIncome;
    }

}
