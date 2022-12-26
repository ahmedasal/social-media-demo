package com.social.media.service;

import com.social.media.model.Post;
import com.social.media.model.Wall;
import com.social.media.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class WallService {
    Wall wall = new Wall();

    // TODO offset and limit;
    public Set<Post> getWallPosts(int userId, int offset, int noOfRows) throws SQLException {
        // select post, postDate, postOwner, updateDate, id from posts where postOwner in
        // (select user1 from users  where user2=? union select user2 from users where user1=?)
        //
        //  PreparedStatement preparedStatement = connection.prepareStatement("Select post,postDate,postOwner,updateDate,posts.id  from posts where postOwner in (select user1 from users where user2 = ? union select user2 from users where user1 = ?") ;
        Set<Post> posts = new LinkedHashSet<>();
        Connection connection = ConnectionHelper.openConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select post, postDate, postOwner, updateDate, id, page_id from posts " +
                    "where postOwner in (select IF(user1=?,user2, user1) as friend from friendship  where user2=? or user1=?)  " +
                    "or  0 < (select count(id) from user_page_like where user = ? and page = page_id) " +
                    "order by updateDate desc limit ? , ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, offset);
            preparedStatement.setInt(6, noOfRows);


            PostService postService = new PostService();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Post post = Post.fromDatabase(resultSet);

                post.setComments(postService.getPostComments(post.getId()));
                post.setImages(postService.getPostImagesIds(post.getId()));
                post.setLikesCount(postService.getLikesCount(post.getId()));
                post.setLikedByMe(postService.likedByMe(userId, post.getId()));
                post.setUsername(postService.getUsername(post.getPostOwner()));
                posts.add(post);
            }
        } finally {
            connection.close();
        }

        return posts;
    }

    public int countWallPosts(int userId, int offset, int noOfRows) throws SQLException {
        int count = 0;
        Connection connection = ConnectionHelper.openConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(id) as co from posts " +
                    "where postOwner in (select IF(user1=?,user2, user1) as friend from friendship  where user2=? or user1=?)  " +
                    "or  0 < (select count(id) from user_page_like where user = ? and page = page_id) " +
                    "order by updateDate desc ");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, userId);


            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt("co");
            }
        } finally {
            connection.close();
        }
        return count;
    }

    //


}
