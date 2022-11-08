package com.social.media.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wall {
    ArrayList<Post> posts = new ArrayList<>();

    Map<Post, Comment> comments = new HashMap<>();

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public Map<Post, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Post, Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "posts=" + posts +
//                ", comments=" + comments +
                '}';
    }
}
