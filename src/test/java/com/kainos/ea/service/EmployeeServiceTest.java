package com.kainos.ea.service;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.exception.DatabaseException;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    EmployeeDao employeeDao = Mockito.mock(EmployeeDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    EmployeeService employeeService = new EmployeeService(employeeDao);

    JobRole jobroles = new JobRole(
            "Test",
            "This a test"
    );

    JobRole jobrole2 = new JobRole(
            0,
            "This a test",
            "test capability"
    );

    Connection conn;

    @Test
    void getJobRoles_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException {
        Mockito.when(employeeDao.getjobroles()).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> employeeDao.getjobroles());
    }

    @Test
    void getViewJobRolesShouldReturnListOfJobRoles_whenDaoReturnsJobRoles() throws SQLException {
        JobRole result = new JobRole("Test","Test");
        JobRole result2 = new JobRole("Test2", "Test2");
        List<JobRole> expected = new ArrayList<>();

        expected.add(result);
        expected.add(result2);

        Mockito.when(employeeDao.getjobroles()).thenReturn(expected);

        List<JobRole> actual;


        actual = employeeDao.getjobroles();

        assertEquals(expected.size(),actual.size());

    }

    @Test
    void getJobCapabilities_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException {
        Mockito.when(employeeDao.getjobwithcapability()).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> employeeDao.getjobwithcapability());
    }

    @Test
    void getViewJobCapabilitiesShouldReturnListOfJobCapabilities_whenDaoReturnsJobCapabilities() throws SQLException {
        JobRole result = new JobRole(0,"Test","Capability 1");
        JobRole result2 = new JobRole(0,"Test","Capability 2");
        List<JobRole> expected = new ArrayList<>();

        expected.add(result);
        expected.add(result2);

        Mockito.when(employeeDao.getjobwithcapability()).thenReturn(expected);

        List<JobRole> actual;


        actual = employeeDao.getjobwithcapability();

        assertEquals(expected.size(),actual.size());

    }
}