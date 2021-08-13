package com.citi.hackathon.PortfolioManager.rest;

import com.citi.hackathon.PortfolioManager.entities.Portfolio;
import com.citi.hackathon.PortfolioManager.entities.User;
import com.citi.hackathon.PortfolioManager.response.UserPortfolio;
import com.citi.hackathon.PortfolioManager.service.PortfolioService;
import com.citi.hackathon.PortfolioManager.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/portfoliomanager")
public class PortfolioController {
    private static Logger logger = LogManager.getLogger(PortfolioController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PortfolioService portfolioService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public Iterable<User> findAllUser(){
        return userService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/portfolios")
    public Iterable<Portfolio> findAllPortfolio(){
        return portfolioService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/userandportfolios")
    public Iterable<UserPortfolio> findAllUserAndPortfolio(){
        return userService.getAllUsersWithPortfolios();
    }

}
