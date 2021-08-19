package com.citi.hackathon.PortfolioManager.rest;

import com.citi.hackathon.PortfolioManager.entities.Stock;
import com.citi.hackathon.PortfolioManager.entities.Transaction;
import com.citi.hackathon.PortfolioManager.entities.User;
import com.citi.hackathon.PortfolioManager.response.StockMarketMover;
import com.citi.hackathon.PortfolioManager.response.UserNetWorth;
import com.citi.hackathon.PortfolioManager.response.UserSummary;
import com.citi.hackathon.PortfolioManager.response.UserTransaction;
import com.citi.hackathon.PortfolioManager.service.StockService;
import com.citi.hackathon.PortfolioManager.service.TransactionService;
import com.citi.hackathon.PortfolioManager.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/portfoliomanager")
public class TransactionController {
    private static Logger logger = LogManager.getLogger(TransactionController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StockService stockService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public Iterable<User> findAllUser(){
        return userService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transactions")
    public Iterable<Transaction> findAllTransaction(){
        return transactionService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/userandtransactions")
    public Iterable<UserTransaction> findAllUserAndTransaction(){
        return userService.getAllUsersWithTransactions();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gettransactionsbyid/{id}")
    public UserTransaction findUserAndTransactionById(@PathVariable("id") int id){
        return userService.getUsersWithTransactionsById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getuserbyid/{id}")
    public User getUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getstockbyid/{id}")
    public Collection<Stock> getStockById(@PathVariable("id") int id){
        return stockService.getStockById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/gettransactionbyid/{id}")
    public Transaction getTransactionById(@PathVariable("id") int id){
        return transactionService.getTransactionById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getStocks")
    public Iterable<Stock> findAllStocks(){
        return stockService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addUser")
    public void addUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addStock")
    public void addStock(@RequestBody Stock stock){
        stockService.addStock(stock);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addTransaction")
    public void addTransaction(@RequestBody Transaction transaction){
        transactionService.addNewTransaction(transaction);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getusersummary/{id}")
    public UserSummary getUserSummary(@PathVariable("id") int id){
        return userService.getUserSummary(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getusernetworth/{id}")
    public UserNetWorth getUserNetWorth(@PathVariable("id") int id) throws ParseException {
        return userService.getUserNetWorth(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getmarketmovers")
    public List<StockMarketMover> getMarketMovers() throws ParseException {
        return stockService.getMarketMovers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getspending/{id}")
    public Double getSpending(@PathVariable("id") int id) throws ParseException {
        return userService.getUserSpending(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getincome/{id}")
    public Float getIncome(@PathVariable("id") int id) throws ParseException {
        return userService.getUserIncome(id);
    }

}
