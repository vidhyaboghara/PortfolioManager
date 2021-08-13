package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.User;
import com.citi.hackathon.PortfolioManager.response.UserSummary;
import com.citi.hackathon.PortfolioManager.response.UserTransaction;

import java.util.Collection;

public interface UserService {
    Collection<UserTransaction> getAllUsersWithTransactions();

    UserTransaction getUsersWithTransactionsById(int id);

    UserSummary getUserSummary(Integer id);

    Collection<User> getAll();

    User getUserById(Integer id);

    User getUserByEmailId(String email);

    void addNewUser(User user);

    void updateUser(User user);

    void deleteUser(int id);
}
