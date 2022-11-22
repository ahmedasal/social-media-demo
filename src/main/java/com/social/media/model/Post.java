package com.social.media.model;

import java.util.ArrayList;
import java.util.Date;

public class Post {
    int id;
    String post;
    Date postDate;
    int postOwner;

    ArrayList<Comment> comments;
    public Post() {

    }

    public Post(String post, Date postDate, int postOwner) {
        this.post = post;
        this.postDate = postDate;
        this.postOwner = postOwner;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public int getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(int postOwner) {
        this.postOwner = postOwner;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", postDate='" + postDate + '\'' +
                ", postOwner=" + postOwner +
                '}';
    }
}
