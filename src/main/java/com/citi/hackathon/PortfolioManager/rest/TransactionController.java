package com.citi.hackathon.PortfolioManager.rest;

import com.citi.hackathon.PortfolioManager.entities.Stock;
import com.citi.hackathon.PortfolioManager.entities.Transaction;
import com.citi.hackathon.PortfolioManager.entities.User;
import com.citi.hackathon.PortfolioManager.response.UserTransaction;
import com.citi.hackathon.PortfolioManager.service.StockService;
import com.citi.hackathon.PortfolioManager.service.TransactionService;
import com.citi.hackathon.PortfolioManager.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.GET, value = "/getStocks")
    public Iterable<Stock> findAllStocks(){
        return stockService.getAll();
    }

}
