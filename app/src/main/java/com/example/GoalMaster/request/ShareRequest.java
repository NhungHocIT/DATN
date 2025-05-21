package com.example.GoalMaster.request;


public class ShareRequest {
    private long userId;
    private long postId;

    public ShareRequest(long userId, long postId) {
        this.userId = userId;
        this.postId = postId;
    }
    //getter
    public long getUserId() {
        return userId;
    }

    public long getPostId() {
        return postId;
    }
    //setter
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public void setPostId(long postId) {
        this.postId = postId;
    }
}
