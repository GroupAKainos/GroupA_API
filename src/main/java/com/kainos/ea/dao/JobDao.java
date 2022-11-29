package com.kainos.ea.dao;

import com.kainos.ea.model.JobRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.kainos.ea.util.DatabaseConnector.closeConnection;
import static com.kainos.ea.util.DatabaseConnector.getConnection;

public class JobDao {

    public List<JobRole> getjobroles() throws SQLException {

        String s = "SELECT job.jobName, job.specification, job.specSummary, jobBandLevel.BandName, jobCapabilities.capabilityName, job.jobResponsibility FROM job JOIN jobCapabilities on job.capabilityId = jobCapabilities.capabilityId JOIN jobBandLevel on job.bandLevelId = jobBandLevel.bandLevelId";

        List<JobRole> jobrole = new ArrayList<>();

        try {
            Connection c = getConnection();
            PreparedStatement preparedStmt1 = c.prepareStatement(s);

            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.executeQuery();
            while (rs.next()) {
                JobRole jobroles = new JobRole(
                        rs.getString("jobName")
                );
                jobroles.setJobResponsibility(rs.getString("jobResponsibility"));
                jobroles.setSpecification(rs.getString("specification"));
                jobroles.setSpecSummary(rs.getString("specSummary"));
                jobroles.setCapabilityName(rs.getString("capabilityName"));
                jobroles.setBandName(rs.getString("bandName"));
                jobroles.setBandLevelID(rs.getInt("bandLevelId"));

                jobrole.add(jobroles);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

        return jobrole;
    }

    public JobRole getSpec(int jobid) throws SQLException {
        String sql = "select specification, specSummary from job where jobid=?";

        JobRole jobRole = new JobRole(jobid);

        try {
            Connection c = getConnection();
            PreparedStatement preparedStmt1 = c.prepareStatement(sql);
            System.out.println(jobid);
            preparedStmt1.setInt(1, jobid);

            ResultSet rs = preparedStmt1.executeQuery();


            while (rs.next()) {
                jobRole.setSpecification(rs.getString("specification"));
                jobRole.setSpecSummary(rs.getString("specSummary"));
            }
        } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        closeConnection();
    }

        return jobRole;
    }
    public JobRole getResponsibility(int jobid) throws SQLException {
        String sql = "select jobResponsibility from job where jobid=?";

        JobRole jobRole = new JobRole(jobid);

        try {
            Connection c = getConnection();
            PreparedStatement preparedStmt1 = c.prepareStatement(sql);
            System.out.println(jobid);
            preparedStmt1.setInt(1, jobid);

            ResultSet rs = preparedStmt1.executeQuery();


            while (rs.next()) {
                jobRole.setJobResponsibility(rs.getString("jobResponsibility"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

        return jobRole;

    }

    public List<JobRole> getjobwithcapability() throws SQLException {

        String s = "SELECT job.jobName, jobCapabilities.capabilityName FROM job JOIN jobCapabilities on job.capabilityId = jobCapabilities.capabilityId";

        List<JobRole> jobcapabilities = new ArrayList<>();

        try {
            Connection c = getConnection();
            PreparedStatement preparedStmt1 = c.prepareStatement(s);

            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.executeQuery();
            while (rs.next()) {

                JobRole capabilities = new JobRole(
                        rs.getString("jobName"),
                        rs.getString("capabilityName")
                );
                jobcapabilities.add(capabilities);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return jobcapabilities;
    }
}
