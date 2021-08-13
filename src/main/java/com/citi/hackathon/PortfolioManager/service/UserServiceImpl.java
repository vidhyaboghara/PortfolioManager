package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Portfolio;
import com.citi.hackathon.PortfolioManager.entities.User;
import com.citi.hackathon.PortfolioManager.repositories.PortfolioRepository;
import com.citi.hackathon.PortfolioManager.repositories.UserRepository;
import com.citi.hackathon.PortfolioManager.response.UserPortfolio;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    private static final Logger logger = LogManager.getLogger(UserRepository.class);

    @Override
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Collection<UserPortfolio> getAllUsersWithPortfolios() {
        Collection<User> users = userRepository.findAll();
        Collection<UserPortfolio> userPortfolios = new ArrayList<>();

        for (User user:users) {
            UserPortfolio userPortfolio = new UserPortfolio();
            userPortfolio.setId(user.getId());
            userPortfolio.setFirstName(user.getFirstName());
            userPortfolio.setLastName(user.getLastName());
            userPortfolio.setEmailId(user.getEmailId());
            userPortfolio.setPhone(user.getPhone());
            userPortfolio.setPortfolioList((List<Portfolio>) portfolioRepository.getByUserId(user.getId()));
            userPortfolios.add(userPortfolio);
        }
        return userPortfolios;
    }

    @Override
    public User getUserById(Integer id){
        return userRepository.getById(id);
    }

    @Override
    public User getUserByEmailId(String email){
        return  userRepository.getByEmailId(email);
    }

    @Override
    public void addNewUser(User user){
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user){
        Optional<User> userInDb = userRepository.findById(user.getId());
        if(userInDb.isPresent()){
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.delete(user.get());
        }
    }
}
