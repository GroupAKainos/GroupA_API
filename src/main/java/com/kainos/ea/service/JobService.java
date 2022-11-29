package com.kainos.ea.service;

import com.kainos.ea.dao.JobDao;
import com.kainos.ea.exception.DatabaseException;
import com.kainos.ea.model.JobRole;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobService {

    public JobDao jobDao;
    public Exception exception;

    public JobService(JobDao dao){
        this.jobDao = dao;
        this.exception = new Exception();
    }

    public List<JobRole> viewJobRoles() throws DatabaseException, SQLException {
        List<JobRole> jobrole = new ArrayList<>();

        jobrole = jobDao.getjobroles();

        if(jobrole.size() <1){
            throw new DatabaseException(exception);
        }

        return jobrole;
    }

    public JobRole getSpecificationJob(int jobid) throws SQLException {
        return jobDao.getSpec(jobid);
    }
    public List<JobRole> viewJobCapabilities() throws DatabaseException, SQLException {
        List<JobRole> jobrole = new ArrayList<>();

        jobrole = jobDao.getjobwithcapability();

        if(jobrole.size() <1){
            throw new DatabaseException(exception);
        }
        return jobrole;
    }

    public JobRole getResponsibilityJob(int jobid) throws SQLException {
        return jobDao.getResponsibility(jobid);
    }

}

