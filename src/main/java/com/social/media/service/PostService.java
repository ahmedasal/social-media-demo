package com.social.media.service;

import com.social.media.crud.PostCrud;
import com.social.media.model.Post;

import java.sql.Connection;
import java.sql.SQLException;

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

}
