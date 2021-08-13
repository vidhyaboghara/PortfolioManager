package com.citi.hackathon.PortfolioManager.response;

import com.citi.hackathon.PortfolioManager.entities.Portfolio;
import com.citi.hackathon.PortfolioManager.entities.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPortfolio extends User {
    private List<Portfolio> portfolioList;
}
