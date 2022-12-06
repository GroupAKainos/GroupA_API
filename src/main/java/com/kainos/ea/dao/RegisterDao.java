package com.kainos.ea.dao;

import com.kainos.ea.exception.EncryptionException;
import com.kainos.ea.exception.InvalidEmailException;
import com.kainos.ea.exception.InvalidPasswordException;
import com.kainos.ea.model.Token;
import com.kainos.ea.model.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.kainos.ea.util.DatabaseConnector.getConnection;

public class RegisterDao {

    public Token doRegister(User user) {
        try {

            if (user.getPassword() == null) {
                throw new InvalidPasswordException();
            }
            if (user.getEmail() == null) {
                throw new InvalidEmailException();
            }

            Connection connection = getConnection();
            String insertQuery = "INSERT INTO user (email, password, role, firstName, lastName) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(insertQuery);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());


            if(preparedStatement.executeUpdate() > 0) {
                Token token = new Token("");
                token.generateToken(user);

                return token;
            }

            return null;

            } catch (InvalidPasswordException | InvalidEmailException | EncryptionException | Exception e) {
                throw new RuntimeException(e);
            }
    }
}
