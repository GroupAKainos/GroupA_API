package com.kainos.ea.service;

import com.kainos.ea.dao.RegisterDao;
import com.kainos.ea.model.Token;
import com.kainos.ea.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    RegisterDao registerDao = Mockito.mock(RegisterDao.class);

    @Test
    void registerUser_shouldReturnToken_whenDaoReturnsToken() throws SQLException {

        User user = new User(
                "Something@anything.com",
                "Password!",
                "Admin",
                "John",
                "Doe"
        );

        Token expectedResult = new Token("token");
        Mockito.when(registerDao.doRegister(user)).thenReturn(expectedResult);

        Token result = new Token("token");

        assertEquals(result.getToken(), expectedResult.getToken());
    }
}
