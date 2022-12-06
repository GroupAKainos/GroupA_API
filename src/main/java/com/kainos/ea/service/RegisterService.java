package com.kainos.ea.service;

import com.kainos.ea.dao.RegisterDao;

import com.kainos.ea.model.Token;
import com.kainos.ea.model.User;

import java.sql.SQLException;

public class RegisterService {

    public RegisterDao registerDao;

    public RegisterService(RegisterDao dao){
        this.registerDao = dao;
    }

    public Token registerUser(User user) throws SQLException {
        return registerDao.doRegister(user);
    }
}
