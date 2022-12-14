package com.social.media.service;

import com.social.media.crud.PostCrud;
import com.social.media.model.Comment;
import com.social.media.model.Image;
import com.social.media.model.Post;
import com.social.media.model.User;
import com.social.media.util.ConnectionHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PostService {
    PostCrud postCrud = new PostCrud();

    public Boolean likedByMe( int userId, int postId) throws SQLException {
        Connection connection = ConnectionHelper.openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(id) as total from likes where user=? and post_id = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("total") > 0;
        } finally {
            connection.close();
        }
    }

    public Post writePost(Connection connection, Post post) throws SQLException {
        postCrud.insert(connection, post);
        return post;
    }

    public Post getPost(Connection connection, Integer id) throws SQLException {
        Post post = postCrud.get(connection, id);
        return post;

    }

    //TODO getPost (postCrud.getPost commentCrud.getPstComments
    public int deletePost(Connection connection, int id) throws SQLException {
        int numberOfRowsDeleted = postCrud.delete(connection, id);
        return numberOfRowsDeleted;
    }

    public ArrayList<Comment> getPostComments(int postId) throws SQLException {
        Connection connection = ConnectionHelper.openConnection();
        ArrayList<Comment> comments = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from comments where post_id = ? order by create_date");
            preparedStatement.setInt(1, postId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                User user = new User();
                comment.setId(resultSet.getInt("id"));
                comment.setPostId(postId);
                comment.setCommentText(resultSet.getString("comment_text"));
                user.setId(resultSet.getInt("user_id"));
                comment.setUser(user);
                comment.setUsername(getUsername(user.getId()));
                comment.setParentCommentId(resultSet.getInt("comment_parent_id"));
                comment.setCreatedDate(resultSet.getString("create_date"));
                comment.setUpdatedDate(resultSet.getString("update_date"));
                comments.add(comment);
            }
        } finally {
            connection.close();
        }

        return comments;
    }

    public int getLikesCount(int postId) throws SQLException {
        Connection connection = ConnectionHelper.openConnection();
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT likes_count FROM socialMediaApp.posts where id= ?;");
            preparedStatement.setInt(1, postId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt("likes_count");
            }
        }
        finally {
            connection.close();
        }
        return count;
    }


    public String getUsername(int id) throws SQLException {
        Connection connection = ConnectionHelper.openConnection();
        String username = null;
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("select username from users where id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
            }
        } finally {
            connection.close();
        }
        return username;
    }

    public Image saveImage(Connection connection, Image img) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into post_images (post_id, image) values(?,?)");
        preparedStatement.setInt(1, img.getPostId());
        preparedStatement.setBlob(2, img.getInputStream());
        preparedStatement.execute();
        return img;
    }

    public Image getImage(int imageId) throws SQLException {
        Connection connection = ConnectionHelper.openConnection();
        Image image = new Image();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from post_images where id = ?");
            preparedStatement.setInt(1, imageId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                image.setId(imageId);
                image.setPostId(rs.getInt("post_id"));
                image.setInputStream(rs.getBlob("image").getBinaryStream());
            }
        } finally {
            connection.close();
        }

        return image;
    }

    public List<Integer> getPostImagesIds(int postId) throws SQLException {
        List<Integer> images = new LinkedList<>();
        Connection connection = ConnectionHelper.openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id from post_images where post_id = ?");
            preparedStatement.setInt(1, postId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                images.add(rs.getInt("id"));
            }
        } finally {
            connection.close();
        }
        return images;
    }

}
