package com.social.media.controllers;


import com.social.media.model.Comment;
import com.social.media.model.FriendRequest;
import com.social.media.model.Friendship;
import com.social.media.model.User;
import com.social.media.service.CommentService;
import com.social.media.service.FriendRequestService;
import com.social.media.service.FriendshipService;
import com.social.media.service.WallService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CommentController {


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialMediaApp", "root", "password");
        FriendshipService friendshipService = new FriendshipService();
        FriendRequestService friendRequestService = new FriendRequestService();
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSenderUser(33);
        friendRequest.setReceiverUser(37);
        friendRequest.setCreateDate("2019-12-07 10:10:10");
        friendRequestService.sendFriendRequest(connection, friendRequest);


        Friendship friendship = new Friendship();
        friendship.setUser1Id(33);
        friendship.setUser2Id(37);
        friendship.setCreateDate("2019-12-10 10:10:10");

        friendshipService.confirmFriendship(connection, friendship);

//        System.out.println(friendshipService.getAllFriends(connection, 33));
//        friendshipService.confirmFriendship(connection, friendship);



        connection.close();


    }

}
