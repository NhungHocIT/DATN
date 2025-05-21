package com.example.GoalMaster;

public class PostComment {

    private int idCmt;
    private long postId;
    private long userId;
    private String content;
    private String cmtAt;

    // Constructor đầy đủ
    public PostComment(int idCmt, long postId, long userId, String content, String cmtAt) {
        this.idCmt = idCmt;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.cmtAt = cmtAt;
    }

    public PostComment() {
    }
    // Getters
    public int getIdCmt() {
        return idCmt;
    }

    public long getPostId() {
        return postId;
    }

    public long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getCmtAt() {
        return cmtAt;
    }

    // Setters
    public void setIdCmt(int idCmt) {
        this.idCmt = idCmt;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCmtAt(String cmtAt) {
        this.cmtAt = cmtAt;
    }
}
