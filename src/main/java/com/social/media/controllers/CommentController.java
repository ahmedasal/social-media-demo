package com.social.media.controllers;



import com.social.media.model.Like;
import com.social.media.service.LikeService;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;


public class CommentController {


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialMediaApp", "root", "password");
        LikeService likeService = new LikeService();
        Like like = new Like();
        like.setUserId(45);
        like.setPostId(3000);
        like.setCreateDate("2019-12-20 10:10:10");


        likeService.likePost(connection, like);


        connection.close();


    }

}
