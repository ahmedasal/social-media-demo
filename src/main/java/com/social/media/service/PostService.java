package com.social.media.service;

import com.social.media.crud.PostCrud;
import com.social.media.model.Comment;
import com.social.media.model.Post;
import com.social.media.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostService {
    PostCrud postCrud = new PostCrud();

    public Post writePost(Connection connection, Post post) throws SQLException {
        postCrud.insert(connection, post);
        return post;
    }

//TODO getPost (postCrud.getPost commentCrud.getPstComments
    public int deletePost(Connection connection, int id) throws SQLException {
        int numberOfRowsDeleted = postCrud.delete(connection, id);
        return numberOfRowsDeleted;
    }

    public ArrayList<Comment> getPostComments (Connection connection, int postId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from comments where post_id = ? order by create_date");
        preparedStatement.setInt(1, postId);
        ArrayList<Comment> comments =  new ArrayList<>();
        Comment comment = new Comment();
        User user = new User();
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            comment.setId(resultSet.getInt("id"));
            comment.setPostId(postId);
            comment.setCommentText(resultSet.getString("comment_text"));
            user.setId(resultSet.getInt("user_id"));
            comment.setUser(user);
            comment.setParentCommentId(resultSet.getInt("comment_parent_id"));
            comment.setCreatedDate(resultSet.getString("create_date"));
            comment.setUpdatedDate(resultSet.getString("update_date"));
            comments.add(comment);
        }
        return comments;
    }

}
