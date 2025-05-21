package com.example.GoalMaster.request;


public class LikeRequest {
    private long userId;
    private long postId;

    public LikeRequest(long userId, long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    // Getter và Setter

    public long getPostId() {
        return postId;
    }
    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }

}