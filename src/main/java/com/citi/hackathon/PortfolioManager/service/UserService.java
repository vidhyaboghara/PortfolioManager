package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.User;
import com.citi.hackathon.PortfolioManager.response.UserPortfolio;

import java.util.Collection;

public interface UserService {
    Collection<UserPortfolio> getAllUsersWithPortfolios();

    Collection<User> getAll();

    User getUserById(Integer id);

    User getUserByEmailId(String email);

    void addNewUser(User user);

    void updateUser(User user);

    void deleteUser(int id);
}