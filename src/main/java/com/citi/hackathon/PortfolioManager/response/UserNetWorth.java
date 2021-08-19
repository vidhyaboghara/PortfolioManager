package com.citi.hackathon.PortfolioManager.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNetWorth {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private Float netWorth;
}
