package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.User;
import com.citi.hackathon.PortfolioManager.response.UserNetWorth;
import com.citi.hackathon.PortfolioManager.response.UserSummary;
import com.citi.hackathon.PortfolioManager.response.UserTransaction;

import java.text.ParseException;
import java.util.Collection;

public interface UserService {
    Float getUserIncome(Integer id) throws ParseException;

    Double getUserSpending(Integer id) throws ParseException;

    Collection<UserTransaction> getAllUsersWithTransactions();

    UserTransaction getUsersWithTransactionsById(int id);

    UserSummary getUserSummary(Integer id);

    UserNetWorth getUserNetWorth(Integer id) throws ParseException;

    Collection<User> getAll();

    User getUserById(Integer id);

    User getUserByEmailId(String email);

    void addNewUser(User user);

    void updateUser(User user);

    void deleteUser(int id);
}
