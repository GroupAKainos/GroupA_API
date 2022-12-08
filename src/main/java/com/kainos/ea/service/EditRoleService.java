package com.kainos.ea.service;

import com.kainos.ea.dao.JobDao;
import com.kainos.ea.exception.DatabaseException;
import com.kainos.ea.exception.NotAValidJobID;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.UpdateRole;

import java.sql.SQLException;

public class EditRoleService {

    public JobDao jobDao;
    public EditRoleService(JobDao dao){
        this.jobDao = dao;
    }


    public int editRole(UpdateRole upd) throws DatabaseException, SQLException {
        return jobDao.editRole(upd);
    }
    public JobRole viewJob(int jobid) throws DatabaseException, SQLException, NotAValidJobID {

        if(jobid < 0){
            throw new NotAValidJobID();
        }

        JobRole id;

        id = jobDao.getRoles(jobid);

        if(id.getJobid() == 0){
            throw new DatabaseException("Error in Job Call", new Exception());
        }
        return jobDao.getRoles(jobid);
    }
}
