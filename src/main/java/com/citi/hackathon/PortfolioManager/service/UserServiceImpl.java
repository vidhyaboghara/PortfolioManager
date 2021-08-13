package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.Transaction;
import com.citi.hackathon.PortfolioManager.entities.User;
import com.citi.hackathon.PortfolioManager.repositories.TransactionRepository;
import com.citi.hackathon.PortfolioManager.repositories.UserRepository;
import com.citi.hackathon.PortfolioManager.response.UserTransaction;
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
    private TransactionRepository transactionRepository;

    private static final Logger logger = LogManager.getLogger(UserRepository.class);

    @Override
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Collection<UserTransaction> getAllUsersWithTransactions() {
        Collection<User> users = userRepository.findAll();
        Collection<UserTransaction> userTransactions = new ArrayList<>();

        for (User user:users) {
            UserTransaction userTransaction = new UserTransaction();
            userTransaction.setId(user.getId());
            userTransaction.setFirstName(user.getFirstName());
            userTransaction.setLastName(user.getLastName());
            userTransaction.setEmailId(user.getEmailId());
            userTransaction.setPhone(user.getPhone());
            userTransaction.setTransactionList((List<Transaction>) transactionRepository.getByUserId(user.getId()));
            userTransactions.add(userTransaction);
        }
        return userTransactions;
    }

    @Override
    public UserTransaction getUsersWithTransactionsById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            UserTransaction userTransaction = new UserTransaction();
            userTransaction.setId(user.get().getId());
            userTransaction.setFirstName(user.get().getFirstName());
            userTransaction.setLastName(user.get().getLastName());
            userTransaction.setEmailId(user.get().getEmailId());
            userTransaction.setPhone(user.get().getPhone());
            userTransaction.setTransactionList((List<Transaction>) transactionRepository.getByUserId(user.get().getId()));
            return userTransaction;
        }
        return null;
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
