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

        String s = "SELECT job.jobName, job.specification, job.specSummary, jobCapabilities.capabilityName FROM job JOIN jobCapabilities on job.capabilityId = jobCapabilities.capabilityId";

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
                jobroles.setSpecification(rs.getString("specification"));
                jobroles.setSpecSummary(rs.getString("specSummary"));
                jobroles.setCapabilityName(rs.getString("capabilityName"));
                jobrole.add(jobroles);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

        return jobrole;
    }

    public JobRole getSpec(String jobName) throws SQLException {
        String sql = "select specification from job where jobName=?";

        JobRole jobRole = new JobRole(jobName);

        try {
            Connection c = getConnection();
            PreparedStatement preparedStmt1 = c.prepareStatement(sql);
            System.out.println(jobName);
            preparedStmt1.setString(1, jobName);

            ResultSet rs = preparedStmt1.executeQuery();


            while (rs.next()) {
                jobRole.setSpecification(rs.getString("specification"));
            }
            System.out.println("test");
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

