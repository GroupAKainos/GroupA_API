package com.kainos.ea.dao;

import com.kainos.ea.exception.EncryptionException;
import com.kainos.ea.exception.InvalidEmailException;
import com.kainos.ea.exception.InvalidPasswordException;
import com.kainos.ea.model.JobRole;
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

public class UserDao {

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

            } catch (SQLException | InvalidPasswordException | InvalidEmailException | EncryptionException e) {
                throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public Token doLogin(User user) {
        try {

            if (user.getPassword() == null) {
                throw new InvalidPasswordException();
            }
            if (user.getEmail() == null) {
                throw new InvalidEmailException();
            }

            Connection connection = getConnection();
            String getQuery = "select password, role from user where email=?";

            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(getQuery);
            preparedStatement.setString(1, user.getEmail());

            ResultSet rs = preparedStatement.executeQuery();


            User resultUser = new User("", "", "", "", "");

            while (rs.next()) {
                resultUser.setEmail(user.getEmail());
                resultUser.setPassword(rs.getString("password"));
                resultUser.setRole(rs.getString("role"));
            }
            if(user.getPassword().equals(resultUser.getPassword())) {
                Token token = new Token("");
                token.generateToken(resultUser);

                return token;
            }


            return null;

        } catch (SQLException | InvalidPasswordException | InvalidEmailException | EncryptionException |
                 NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
