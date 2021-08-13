package com.citi.hackathon.PortfolioManager.service;

import com.citi.hackathon.PortfolioManager.entities.User;
import com.citi.hackathon.PortfolioManager.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LogManager.getLogger(UserRepository.class);

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
        if(userRepository.getById(user.getId()) != null){
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
