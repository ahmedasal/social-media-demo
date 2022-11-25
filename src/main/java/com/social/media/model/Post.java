package com.social.media.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    int id;
    String post;
    Date postDate;
    int postOwner;
    int likesCount;
    int commentsCount;

    List<Comment> comments;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", postDate=" + postDate +
                ", postOwner=" + postOwner +
                ", likesCount=" + likesCount +
                ", commentsCount=" + commentsCount +
                ", comments=" + comments +
                '}';
    }
}
