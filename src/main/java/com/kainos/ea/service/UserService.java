package com.kainos.ea.service;

import com.kainos.ea.dao.UserDao;

import com.kainos.ea.model.Token;
import com.kainos.ea.model.User;

import java.sql.SQLException;

public class UserService {

    public UserDao userDao;

    public UserService(UserDao dao){
        this.userDao = dao;
    }

    public Token registerUser(User user) throws SQLException {
        return userDao.doRegister(user);
    }

    public Token loginUser(User user) throws SQLException {
        return userDao.doLogin(user);
    }
}
