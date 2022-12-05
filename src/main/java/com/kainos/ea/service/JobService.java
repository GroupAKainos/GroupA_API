package com.kainos.ea.service;

import com.kainos.ea.dao.JobDao;
import com.kainos.ea.exception.DatabaseException;
import com.kainos.ea.exception.NotAValidBandLevelException;
import com.kainos.ea.model.Competency;
import com.kainos.ea.model.JobRole;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobService {
    public JobDao jobDao;

    public JobService(JobDao dao){
        this.jobDao = dao;
    }

    public List<JobRole> viewJobRoles() throws DatabaseException, SQLException {
        List<JobRole> jobrole = new ArrayList<>();

        jobrole = jobDao.getjobroles();

        if(jobrole.size() <1){
            throw new DatabaseException("Error in view job roles", new Exception());
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
            throw new DatabaseException("Error in view job capabilities ", new Exception());
        }
        return jobrole;
    }

    public JobRole getResponsibilityJob(int jobid) throws SQLException {
        return jobDao.getResponsibility(jobid);
    }

    public List<Competency> competency(int bandID) throws DatabaseException, SQLException, NotAValidBandLevelException {

        if(bandID < 0 || bandID > 8){
            throw new NotAValidBandLevelException();
        }

        List<Competency> comp;

        comp = jobDao.getCompetency(bandID);

        if(comp.size() <1){
            throw new DatabaseException("Error in competency call", new Exception());
        }

        return comp;
    }

    public Boolean deleteJobRole(int jobId) throws SQLException {
       return jobDao.deleteJobRole(jobId);
    }

}

