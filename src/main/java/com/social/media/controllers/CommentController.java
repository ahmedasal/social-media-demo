package com.social.media.controllers;



import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;


public class CommentController {


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialMediaApp", "root", "password");



        connection.close();


    }

}
