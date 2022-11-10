package com.social.media.controllers;


import com.social.media.model.Comment;
import com.social.media.service.CommentService;
import com.social.media.service.PostService;
import com.social.media.service.WallService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentController {


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialMediaApp", "root", "password");

        PostService postService = new PostService();

        CommentService commentService = new CommentService();

        ArrayList<Comment> comments =  commentService.getCommentchildren(connection, 10);
        System.out.println(comments);

        connection.close();


    }

}
