package com.social.media.service;

import com.social.media.model.Comment;
import com.social.media.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentService {
    public ArrayList<Comment> getCommentchildren(Connection connection, int commentId) throws SQLException {
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        User user = new User();
        PreparedStatement preparedStatement = connection.prepareStatement("select c.comment_text , c.id, p.id as parent_id, c.user_id  ,p.comment_text , c.create_date , c.update_date from comments p , comments c  where c.comment_parent_id = p.id and p.id = 26 ;");

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            comment.setId(resultSet.getInt("id"));
            comment.setCommentText(resultSet.getString("comment_text"));
            user.setId(resultSet.getInt("user_id"));
            comment.setUser(user);
            comment.setParentCommentId(resultSet.getInt("parent_id"));
            comment.setCreatedDate(resultSet.getString("create_date"));
            comment.setUpdatedDate(resultSet.getString("update_date"));


            comments.add(comment);

        }








        return comments;
    }
    

}
