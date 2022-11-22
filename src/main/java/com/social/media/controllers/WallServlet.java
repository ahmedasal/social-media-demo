package com.social.media.controllers;


import com.social.media.model.Post;
import com.social.media.model.User;
import com.social.media.service.PostService;
import com.social.media.service.WallService;
import com.social.media.util.ConnectionHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class WallServlet extends HttpServlet {

    WallService wallService = new WallService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null;
        User user = (User) req.getSession().getAttribute("currentUser");
        try {
            connection = ConnectionHelper.openConnection();
            Set<Post> posts = wallService.getWallPosts(connection , user.getId());
            req.setAttribute("posts", posts);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/home.jsp");
            requestDispatcher.forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null;
        PostService postService = new PostService();
        User user = (User)req.getSession().getAttribute("currentUser");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        String date1 = formatter.format(date);

        Post post = new Post();
        post.setPost(req.getParameter("postText"));
        post.setPostOwner(user.getId());
        post.setPostDate(new Date());

        try {
            connection = ConnectionHelper.openConnection();
            if(post.getPost() != null && post.getPost().length() > 0) {
                postService.writePost(connection, post);
                req.setAttribute("postAdded","post is added successfully");
//                req.getRequestDispatcher("/views/home.jsp").forward(req, resp);

            }else{
                req.setAttribute("postAdded","Please enter your post");
            }

            doGet(req, resp);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
