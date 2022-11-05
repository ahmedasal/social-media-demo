package com.social.media.controllers;


import com.social.media.crud.CommentCrud;
import com.social.media.model.Comment;
import com.social.media.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommentController {


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialMediaApp", "root", "password");

        CommentCrud commentCrud = new CommentCrud();
        User user = new User();
        user.setId(10);
        String commentText = "yessss!";
        Comment comment = new Comment("yessssss!", 1, user, null, "2019-12-31 23:40:10", null);
        comment.setId(25);
        commentCrud.update(connection, comment);


//        System.out.println(commentCrud.delete(connection, 10));
//        commentCrud.insert(connection, comment);


        connection.close();


    }


}
