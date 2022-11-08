package com.social.media.controllers;


import com.social.media.crud.FriendshipCrud;
import com.social.media.service.WallService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommentController {


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialMediaApp", "root", "password");

        WallService wallService = new WallService();
        int x = 33;
        System.out.println(wallService.getWall(connection,x));



        connection.close();


    }

}
