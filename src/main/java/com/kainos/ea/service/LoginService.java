package com.kainos.ea.service;

import com.kainos.ea.dao.LoginDao;
import com.kainos.ea.model.Token;
import com.kainos.ea.model.User;

import java.sql.SQLException;

public class LoginService {

    public LoginDao loginDao;

    public LoginService(LoginDao dao){
        this.loginDao = dao;
    }

    public Token loginUser(User user) throws SQLException {
        return loginDao.doLogin(user);
    }
}
