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

    public Wall getWall(Connection connection, int PostOwnerId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Select distinct post,postDate,postOwner,updateDate,posts.id  from posts,friendship,users where user2 = postOwner and  users.id = user1 and user1 = 32 order by postDate ");
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

        wall.setPosts(posts);

        return wall;
    }


}
