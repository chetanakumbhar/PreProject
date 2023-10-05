package com.niit.AuthApp.service;

import com.niit.AuthApp.domain.User;

public interface IUserService {
    public User register(User user);
    public User checkLogin(User user);
}
