package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Portfolio;
import com.citi.hackathon.PortfolioManager.repositories.PortfolioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    @Autowired
    private PortfolioRepository portfolioRepository;

    private static final Logger logger = LogManager.getLogger(PortfolioRepository.class);

    @Override
    public Collection<Portfolio> getAll() {
        return portfolioRepository.findAll();
    }

    @Override
    public Portfolio getPortfolioById(Integer id){
        return portfolioRepository.getById(id);
    }

    @Override
    public Collection<Portfolio> getPortfoliosByUserId(Integer userId){
        return portfolioRepository.getByUserId(userId);
    }

    @Override
    public void addNewPortfolio(Portfolio portfolio){
        portfolioRepository.save(portfolio);
    }

    @Override
    public void updatePortfolio(Portfolio portfolio){
        Optional<Portfolio> portfolioInDb = portfolioRepository.findById(portfolio.getId());
        if(portfolioInDb.isPresent()){
            portfolioRepository.save(portfolio);
        }
    }

    @Override
    public void deletePortfolio(int id){
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);
        if(portfolio.isPresent()){
            portfolioRepository.delete(portfolio.get());
        }
    }
}
