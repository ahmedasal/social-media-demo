package com.social.media.service;

import com.social.media.model.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LikeService {
    // add like
    public Like likePost(Connection connection, Like like) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into likes(user, create_date,post_id) values (?,?,?)");
        preparedStatement.setInt(1, like.getUserId());
        preparedStatement.setString(2, like.getCreateDate());
        preparedStatement.setInt(3, like.getPostId());
        preparedStatement.execute();
        return like;
    }


    public int deleteLike(Connection connection, int likeId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from likes where id =? ;");
        preparedStatement.setInt(1, likeId);
        int count = preparedStatement.executeUpdate();
        preparedStatement.close();

        return count;
    }


}
