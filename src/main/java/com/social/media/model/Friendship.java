package com.social.media.model;

public class Friendship {
    int id;
    User user1;
    User user2;
    String CreateDate;

    public Friendship() {
    }

    public Friendship(User user1, User user2, String createDate) {
        this.user1 = user1;
        this.user2 = user2;
        CreateDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", user1=" + user1 +
                ", user2=" + user2 +
                ", CreateDate='" + CreateDate + '\'' +
                '}';
    }
}
