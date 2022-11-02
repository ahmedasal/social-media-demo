package com.social.media.crud;

import com.social.media.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostCrud implements Crud<Post, Integer> {

    @Override
    public Post insert(Connection connection, Post post) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into posts (post, postDate, postOwner) values (?,?,?)");

        preparedStatement.setString(1, post.getPost());
        preparedStatement.setString(2, post.getPostDate());
        preparedStatement.setInt(3, post.getPostOwner());

        preparedStatement.execute();
        preparedStatement.close();

        return post;
    }


    @Override
    public Post get(Connection connection, Integer id) throws SQLException {
        Post post = new Post();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from posts where id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            post.setId(id);
            post.setPost(resultSet.getString("post"));
            post.setPostOwner(resultSet.getInt("postOwner"));
            post.setPostDate(resultSet.getString("postDate"));


        }
        preparedStatement.close();
        resultSet.close();
        return post;
    }

    @Override
    public Post update(Connection connection, Post post) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("update posts set post = ? , postDate = ?, postOwner = ? where id = ?");

        preparedStatement.setString(1, post.getPost());
        preparedStatement.setString(2, post.getPostDate());
        preparedStatement.setInt(3, post.getPostOwner());
        preparedStatement.setInt(4, post.getId());


        preparedStatement.execute();
        preparedStatement.close();
        return post;
    }

    @Override
    public int delete(Connection connection, Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("Delete from posts where id=?");

        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        preparedStatement.close();
        return count;
    }
}
