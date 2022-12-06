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

public class LoginDao {

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
