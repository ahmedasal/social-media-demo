package com.social.media.service;

import com.social.media.crud.PageCrud;
import com.social.media.model.Page;
import com.social.media.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PageService {

    //create new page.
    public Page createNewPage(Connection connection, Page page) throws SQLException {
        PageCrud pageCrud = new PageCrud();
        pageCrud.insert(connection, page);
        return page;
    }

    //delete page.
    public int deletePage(Connection connection, int id) throws SQLException {
        PageCrud pageCrud = new PageCrud();
        int count = pageCrud.delete(connection, id);
        return count;
    }

    //add post to page.
    public Post addPostToPage(Connection connection, Post post) throws SQLException {
        PostService postService = new PostService();
        postService.writePost(connection, post);
        return post;
    }
    //show all page posts.
    public List<Post> showPagePosts(Connection connection, int pageId , int userId) throws SQLException {
        List<Post> posts = new LinkedList<>();
        PostService postService = new PostService();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from posts where page_id = ? order by postDate desc");
        preparedStatement.setInt(1, pageId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Post post = new Post();
            post.setPageId(pageId);
            post.setId(resultSet.getInt("postId"));
            post.setPostDate(resultSet.getDate("postDate"));
            post.setPostOwner(resultSet.getInt("postOwner"));
            post.setPost(resultSet.getString("post"));
            post.setComments(postService.getPostComments(connection, resultSet.getInt("postId")));
            post.setLikesCount(postService.getLikesCount(connection, post.getId()));
            post.setLikedByMe(postService.likedByMe(connection, userId, post.getId()));
            post.setUsername(postService.getUsername(connection, post.getPostOwner()));
            posts.add(post);
        }
        return posts;
    }





}
