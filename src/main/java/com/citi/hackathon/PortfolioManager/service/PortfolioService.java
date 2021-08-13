package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Portfolio;

import java.util.Collection;

public interface PortfolioService {
    Portfolio getPortfolioById(Integer id);

    Collection<Portfolio> getPortfoliosByUserId(Integer userId);

    void addNewPortfolio(Portfolio portfolio);

    void updatePortfolio(Portfolio portfolio);

    void deletePortfolio(int id);
}
