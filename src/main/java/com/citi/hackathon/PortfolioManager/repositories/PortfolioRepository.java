package com.citi.hackathon.PortfolioManager.repositories;

import com.citi.hackathon.PortfolioManager.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    Collection<Portfolio> getByUserId(Integer userId);
}
