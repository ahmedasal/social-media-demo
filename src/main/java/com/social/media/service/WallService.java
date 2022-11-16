package com.social.media.service;

import com.social.media.model.Post;
import com.social.media.model.Wall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WallService {
    Wall wall = new Wall();

    public ArrayList<Post> getWallPosts(Connection connection, int PostOwnerId) throws SQLException {
        // select post, postDate, postOwner, updateDate, id from posts where postOwner in
        // (select user1 from users  where user2=? union select user2 from users where user1=?)
        //
      //  PreparedStatement preparedStatement = connection.prepareStatement("Select post,postDate,postOwner,updateDate,posts.id  from posts where postOwner in (select user1 from users where user2 = ? union select user2 from users where user1 = ?") ;
        PreparedStatement preparedStatement = connection.prepareStatement("select post, postDate, postOwner, updateDate, id from posts where postOwner in (select user1 from users  where user2=? union select user2 from users where user1=?)");
        preparedStatement.setInt(1, PostOwnerId);
        Post post = new Post();
        ArrayList<Post> posts = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            post.setId(resultSet.getInt("posts.id"));
            post.setPostDate(resultSet.getString("postDate"));
            post.setPostOwner(resultSet.getInt("postOwner"));
            post.setPost(resultSet.getString("post"));
            posts.add(post);
        }

        return posts;
    }








}
