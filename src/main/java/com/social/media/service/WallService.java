package com.social.media.service;

import com.social.media.model.Post;
import com.social.media.model.Wall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class WallService {
    Wall wall = new Wall();
// TODO offset and limit;
    public Set<Post> getWallPosts(Connection connection, int userId) throws SQLException {
        // select post, postDate, postOwner, updateDate, id from posts where postOwner in
        // (select user1 from users  where user2=? union select user2 from users where user1=?)
        //
      //  PreparedStatement preparedStatement = connection.prepareStatement("Select post,postDate,postOwner,updateDate,posts.id  from posts where postOwner in (select user1 from users where user2 = ? union select user2 from users where user1 = ?") ;
        PreparedStatement preparedStatement = connection.prepareStatement("select post, postDate, postOwner, updateDate, id as postId from posts where postOwner in (select IF(user1=?,user2, user1) as friend from friendship  where user2=? or user1=?)");
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, userId);
        preparedStatement.setInt(3, userId);
        Set<Post> posts = new LinkedHashSet<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Post post = new Post();
            post.setId(resultSet.getInt("postId"));
            post.setPostDate(resultSet.getDate("postDate"));
            post.setPostOwner(resultSet.getInt("postOwner"));
            post.setPost(resultSet.getString("post"));
            posts.add(post);
        }

        return posts;
    }

    //








}
