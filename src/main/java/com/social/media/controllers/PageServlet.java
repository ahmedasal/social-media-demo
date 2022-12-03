package com.social.media.controllers;

import com.social.media.model.Page;
import com.social.media.model.Post;
import com.social.media.model.User;
import com.social.media.service.PageService;
import com.social.media.util.ConnectionHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PageServlet extends HttpServlet {

    Page page = new Page();
    String pageName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        User user = (User) req.getSession().getAttribute("currentUser");
        PageService pageService = new PageService();

        try {
            connection = ConnectionHelper.openConnection();
            pageName = req.getParameter("choosepage");
            req.setAttribute("pagetitle", pageName);
            page.setId(pageService.getPageId(connection, pageName));
            List<Post> pagePosts = pageService.showPagePosts(connection, page.getId(), user.getId());
            req.setAttribute("pagePosts", pagePosts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/page.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PageService pageService = new PageService();
        User user = (User) req.getSession().getAttribute("currentUser");
        Post post = new Post();
        post.setPost(req.getParameter("postText"));
        post.setPostOwner(user.getId());
        post.setPostDate(new Date());
        post.setPageId(page.getId());


        try {
            connection = ConnectionHelper.openConnection();
            if (post.getPost() != null && post.getPost().length() > 0) {
                pageService.addPostToPage(connection, post);
                req.setAttribute("postAdded", "post is added successfully");
//                req.getRequestDispatcher("/views/home.jsp").forward(req, resp);

            } else {
                req.setAttribute("postAdded", "Please enter your post");
            }
            List<Post> pagePosts = pageService.showPagePosts(connection, page.getId(), user.getId());
            req.setAttribute("pagetitle", pageName);

            req.setAttribute("pagePosts", pagePosts);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/page.jsp");
            requestDispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}

