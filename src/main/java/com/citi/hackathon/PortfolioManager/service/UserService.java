package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.User;

public interface UserService {
    User getUserById(Integer id);

    User getUserByEmailId(String email);

    void addNewUser(User user);

    void updateUser(User user);

    void deleteUser(int id);
}
