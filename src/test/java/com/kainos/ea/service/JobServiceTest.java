package com.kainos.ea.service;

import com.kainos.ea.dao.JobDao;
import com.kainos.ea.model.JobRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    JobDao jobDao = Mockito.mock(JobDao.class);

    @Test
    void getJobRoles_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException {
        Mockito.when(jobDao.getjobroles()).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> jobDao.getjobroles());
    }

    @Test
    void getViewJobRolesShouldReturnListOfJobRoles_whenDaoReturnsJobRoles() throws SQLException {
        JobRole result = new JobRole("Test");
        JobRole result2 = new JobRole("Test2");
        List<JobRole> expected = new ArrayList<>();

        expected.add(result);
        expected.add(result2);

        Mockito.when(jobDao.getjobroles()).thenReturn(expected);

        List<JobRole> actual;


        actual = jobDao.getjobroles();

        assertEquals(expected.size(),actual.size());

    }

    @Test
    void getSpec_ShouldReturnJobSpec_whenDaoReturnsJob() throws SQLException {
        JobRole result = new JobRole("Test","Test","Test");
        result.setSpecification("Test");

        Mockito.when(jobDao.getSpec("Test")).thenReturn(result);

        JobRole actual = jobDao.getSpec("Test");

        assertEquals(result.getSpecification(),actual.getSpecification());

    }
}