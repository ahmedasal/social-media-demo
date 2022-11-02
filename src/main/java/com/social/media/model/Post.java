package com.social.media.model;

import java.util.List;

public class Post {
    int id;
    String post;
    String postDate;
    int postOwner;
//List comments
    public Post() {
    }

    public Post(String post, String postDate, int postOwner) {
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

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public int getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(int postOwner) {
        this.postOwner = postOwner;
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