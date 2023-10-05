package com.niit.AuthApp.service;

import com.niit.AuthApp.domain.User;
import com.niit.AuthApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User register(User user) {
        return userRepository.save(user) ;
    }

    @Override
    public User checkLogin(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
    }
}
