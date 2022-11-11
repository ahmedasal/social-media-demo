package com.social.media.controllers;


import com.social.media.model.Comment;
import com.social.media.model.User;
import com.social.media.service.CommentService;
import com.social.media.service.PostService;
import com.social.media.service.UserService;
import com.social.media.service.WallService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentController {


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialMediaApp", "root", "password");
        User user = new User(39,"elkebeerawee","elkebeer", "aweee","0000","1998-05-25","ahmed.asal520@gmail.com");
        UserService userService = new UserService();
        userService.register(connection, user);


        connection.close();


    }

}
