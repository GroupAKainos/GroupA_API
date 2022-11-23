package com.kainos.ea.service;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.exception.DatabaseException;
import com.kainos.ea.model.JobRole;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    public com.kainos.ea.dao.EmployeeDao EmployeeDao;
    public Exception exception;

    public EmployeeService(EmployeeDao dao){
        this.EmployeeDao = dao;
        this.exception = new Exception();
    }

    public List<JobRole> viewJobRoles() throws DatabaseException, SQLException {
        List<JobRole> jobrole = new ArrayList<>();

        jobrole = EmployeeDao.getjobroles();

        if(jobrole.size() <1){
            throw new DatabaseException(exception);
        }

        return jobrole;
    }

    public List<JobRole> viewJobCapabilities() throws DatabaseException, SQLException {
        List<JobRole> jobrole = new ArrayList<>();

        jobrole = EmployeeDao.getjobwithcapability();

        if(jobrole.size() <1){
            throw new DatabaseException(exception);
        }

        return jobrole;
    }
}
