package com.citi.hackathon.PortfolioManager.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserSummary {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private List<Stock> stocks;
}
