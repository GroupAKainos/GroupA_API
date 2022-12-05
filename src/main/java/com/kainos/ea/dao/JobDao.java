package com.kainos.ea.dao;

import com.kainos.ea.exception.RoleNotAddedException;
import com.kainos.ea.model.Competency;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.NewRole;
import com.kainos.ea.model.UpdateRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.kainos.ea.util.DatabaseConnector.closeConnection;
import static com.kainos.ea.util.DatabaseConnector.getConnection;

public class JobDao {

    public List<JobRole> getjobroles() throws SQLException {



        List<JobRole> jobrole = new ArrayList<>();

        try (Connection conn = getConnection()){
            String s = "SELECT job.jobId, job.jobName, job.specification, job.specSummary, jobBandLevel.BandName, job.bandLevelId, jobCapabilities.capabilityName, job.jobResponsibility FROM job JOIN jobCapabilities on job.capabilityId = jobCapabilities.capabilityId JOIN jobBandLevel on job.bandLevelId = jobBandLevel.bandLevelId";
            PreparedStatement preparedStmt1 = Objects.requireNonNull(conn).prepareStatement(s);

            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.executeQuery();
            while (rs.next()) {
                JobRole jobroles = new JobRole(
                        rs.getString("jobName")
                );
                jobroles.setJobid(rs.getInt("jobId"));
                jobroles.setJobResponsibility(rs.getString("jobResponsibility"));
                jobroles.setSpecification(rs.getString("specification"));
                jobroles.setSpecSummary(rs.getString("specSummary"));
                jobroles.setCapabilityName(rs.getString("capabilityName"));
                jobroles.setBandLevelID(rs.getInt("bandLevelId"));
                jobroles.setBandLevelName(rs.getString("bandName"));
                jobrole.add(jobroles);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jobrole;
    }

    public JobRole getSpec(int jobid) throws SQLException {

        JobRole jobRole = new JobRole(jobid);

        try (Connection conn = getConnection()){
            String sql = "select specification, specSummary from job where jobid=?";
            PreparedStatement preparedStmt1 = Objects.requireNonNull(conn).prepareStatement(sql);
            preparedStmt1.setInt(1, jobid);

            ResultSet rs = preparedStmt1.executeQuery();


            while (rs.next()) {
                jobRole.setSpecification(rs.getString("specification"));
                jobRole.setSpecSummary(rs.getString("specSummary"));
            }
        } catch (SQLException ex) {
        ex.printStackTrace();
    }
        return jobRole;
    }

    public JobRole getResponsibility(int jobid) throws SQLException {


        JobRole jobRole = new JobRole(jobid);

        try (Connection conn = getConnection()){
            String sql = "select jobResponsibility from job where jobid=?";
            PreparedStatement preparedStmt1 = Objects.requireNonNull(conn).prepareStatement(sql);
            System.out.println(jobid);
            preparedStmt1.setInt(1, jobid);

            ResultSet rs = preparedStmt1.executeQuery();


            while (rs.next()) {
                jobRole.setJobResponsibility(rs.getString("jobResponsibility"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jobRole;
    }

    public List<JobRole> getjobwithcapability() throws SQLException {

        List<JobRole> jobcapabilities = new ArrayList<>();

        try (Connection conn = getConnection()){
            String s = "SELECT job.jobName, jobCapabilities.capabilityName FROM job JOIN jobCapabilities on job.capabilityId = jobCapabilities.capabilityId";
            PreparedStatement preparedStmt1 = Objects.requireNonNull(conn).prepareStatement(s);

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
        }
        return jobcapabilities;
    }

    public List<Competency> getCompetency(int bandID) throws SQLException {

        List<Competency> Competency = new ArrayList<>();

        try (Connection conn = getConnection()){
            String s = "SELECT competency.competencyName, competency_band.competencyId, competency_band.subheading, competency_band.information\n" +
                    "FROM competency_band\n" +
                    "INNER JOIN competency ON competency.competencyId = competency_band.competencyId\n" +
                    "WHERE competency_band.bandLevelId=?\n" +
                    "ORDER BY competency.competencyID ASC;";
            PreparedStatement preparedStmt1 = Objects.requireNonNull(conn).prepareStatement(s);
            preparedStmt1.setInt(1, bandID);
            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.executeQuery();
            while (rs.next()) {
                Competency comp = new Competency(
                        rs.getInt("competencyId"),
                        rs.getString("subheading"),
                        rs.getString("information")
                );
                Competency.add(comp);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Competency;
    }

    public List<JobRole> populateFamilyLists() throws SQLException {

        List<JobRole> jobRoles = new ArrayList<>();

        try (Connection conn = getConnection()){
            String family ="SELECT jobFamilyId, familyName from jobFamily";
            PreparedStatement preparedStmt1 = Objects.requireNonNull(conn).prepareStatement(family);

            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.executeQuery();

            while (rs.next()) {
                JobRole job = new JobRole(
                  rs.getInt("jobFamilyId"),
                  rs.getString("familyName")
                );

                jobRoles.add(job);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jobRoles;
    }

    public List<JobRole> populateCapabilityList() throws SQLException {

        List<JobRole> jobRoles = new ArrayList<>();

        try (Connection conn = getConnection()){
            String capability = "SELECT capabilityId, capabilityName from jobCapabilities";

            PreparedStatement preparedStmt1 = Objects.requireNonNull(conn).prepareStatement(capability);

            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.executeQuery();

            while (rs.next()) {
                JobRole job = new JobRole(
                        rs.getInt("capabilityId"),
                        rs.getString("capabilityName")
                );

                jobRoles.add(job);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jobRoles;
    }

    public List<JobRole> populateBandLevelList() throws SQLException {

        List<JobRole> jobRoles = new ArrayList<>();

        try (Connection conn = getConnection()){
            String band = "SELECT bandLevelId, BandName from jobBandLevel";
            PreparedStatement preparedStmt1 = Objects.requireNonNull(conn).prepareStatement(band);

            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.executeQuery();

            while (rs.next()) {
                JobRole job = new JobRole(
                        rs.getInt("bandLevelId"),
                        rs.getString("BandName")
                );

                jobRoles.add(job);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return jobRoles;
    }

    public NewRole postNewjobrole (NewRole newRole) throws SQLException, RoleNotAddedException {
        int id =0;
        try (Connection conn = getConnection()){
            String band = "INSERT INTO job (jobName, jobResponsibility, specSummary, bandLevelId, jobFamilyId, capabilityId) VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStmt1 = Objects.requireNonNull(conn).prepareStatement(band, Statement.RETURN_GENERATED_KEYS);
            preparedStmt1.setString(1, newRole.getJobName());
            preparedStmt1.setString(2, newRole.getJobResponsibility());
            preparedStmt1.setString(3, newRole.getSpecSummary());
            preparedStmt1.setInt(4, newRole.getBandLevelID());
            preparedStmt1.setInt(5, newRole.getJobFamilyID());
            preparedStmt1.setInt(6, newRole.getCapabilityID());
            preparedStmt1.execute();

            ResultSet rs = preparedStmt1.getGeneratedKeys();

            while (rs.next()) {
             id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if(id < 0){
        throw new RoleNotAddedException("Role has not been added", new Exception());
        }
        return newRole;
    }

    public Boolean deleteJobRole(Integer jobId) throws SQLException {
        if (jobId == null || !(jobId.intValue() > 0) ){
            throw new SQLException("Job ID may not be null");
        }

        try {
            Connection c = getConnection();
            String sql = "Delete from job where jobId = ?";
            PreparedStatement preparedStmt1 = c.prepareStatement(sql);
            preparedStmt1.setInt(1, jobId);
            preparedStmt1.execute();
            preparedStmt1.close();
            c.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public int editRole(UpdateRole upd) throws SQLException {

        String s = "UPDATE job SET jobName = ?, specSummary = ?, bandLevelId =?, capabilityId =?, jobResponsibility=? WHERE jobid=?";

        Connection c = getConnection();
        PreparedStatement preparedStmt = c.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setString(1, upd.getJobName());
        preparedStmt.setString(2, upd.getSpecSummary());
        preparedStmt.setInt(3, upd.getBandLevelId());
        preparedStmt.setInt(4, upd.getCapabilityId());
        preparedStmt.setString(5, upd.getJobResponsibility());
        preparedStmt.setInt(6, upd.getJobid());

        int affectedRows = preparedStmt.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating user failed, no rows affected.");
        }

        int empNo = 0;

        try (ResultSet rs = preparedStmt.getGeneratedKeys()) {
            if (rs.next()) {
                empNo = rs.getInt(1);
            }
        }

        return empNo;
    }
    public JobRole getRoles(int jobid) throws SQLException {

        String s = "SELECT jobId, jobName, specSummary, bandLevelId, capabilityId, jobResponsibility FROM job WHERE jobId=?";

        JobRole updRole = new JobRole(jobid);

        try {
            Connection c = getConnection();
            PreparedStatement preparedStmt1 = c.prepareStatement(s);
            preparedStmt1.setInt(1, jobid);

            ResultSet rs = preparedStmt1.executeQuery();
            while (rs.next()) {
                updRole.setJobid(rs.getInt("jobid"));
                updRole.setJobName(rs.getString("jobName"));
                updRole.setSpecSummary(rs.getString("specSummary"));
                updRole.setBandLevelID(rs.getInt("bandLevelID"));
                updRole.setCapabilityID(rs.getInt("capabilityId"));
                updRole.setJobResponsibility(rs.getString("jobResponsibility"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return updRole;
    }
}