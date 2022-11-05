package com.social.media.model;

public class FriendRequest {
    int id;
    User user1;
    User user2;
    String CreateDate;
    String comfirmStatus;
    String comfirmDate;

    public FriendRequest() {
        }

    public FriendRequest(User user1, User user2, String createDate, String comfirmStatus, String comfirmDate) {
        this.user1 = user1;
        this.user2 = user2;
        CreateDate = createDate;
        this.comfirmStatus = comfirmStatus;
        this.comfirmDate = comfirmDate;
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

    public String getComfirmStatus() {
        return comfirmStatus;
    }

    public void setComfirmStatus(String comfirmStatus) {
        this.comfirmStatus = comfirmStatus;
    }

    public String getComfirmDate() {
        return comfirmDate;
    }

    public void setComfirmDate(String comfirmDate) {
        this.comfirmDate = comfirmDate;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", user1=" + user1 +
                ", user2=" + user2 +
                ", CreateDate='" + CreateDate + '\'' +
                ", comfirmStatus='" + comfirmStatus + '\'' +
                ", comfirmDate='" + comfirmDate + '\'' +
                '}';
    }
}
