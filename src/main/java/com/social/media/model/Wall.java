package com.social.media.model;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

public class Wall {
    ArrayList<Post> posts = new ArrayList<>();



    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }


    @Override
    public String toString() {
        return "Wall{" +
                "posts=" + posts +
                '}';
    }
}
