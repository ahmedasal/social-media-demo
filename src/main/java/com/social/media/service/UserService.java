package com.social.media.service;

import com.social.media.crud.UserCrud;
import com.social.media.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    UserCrud userCrud = new UserCrud();

    public User register(Connection connection, User user) throws SQLException {
        // TODO add user validation (add to user crud getUserByEmail return null if not exist)
        PreparedStatement preparedStatement = connection.prepareStatement("select email from users where email=? ");
        preparedStatement.setString(1, user.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println("the user already exists");
        } else {
            user = userCrud.insert(connection, user);
        }
        return user;
    }


    public User login(Connection connection, String username, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select id from users where username = ? and password =?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        int id = 0;
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("id");

        }
        User user = userCrud.get(connection, id);
        if (id == 0) {
            return null;
        }

        return user;
    }
}