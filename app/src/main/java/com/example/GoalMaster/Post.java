package com.example.GoalMaster;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private long id;            // ID bài viết
    private String username;    // Tên người dùng đăng bài
    private String time;        // Thời gian đăng bài
    private String content;     // Nội dung bài viết
    private boolean isLiked;    // Trạng thái đã like hay chưa
    private List<String> comments; // Danh sách bình luận dạng String (nếu cần có thể thay bằng PostComment)

    // Constructor
    public Post(long id, String username, String time, String content) {
        this.id = id;
        this.username = username;
        this.time = time;
        this.content = content;
        this.isLiked = false;
        this.comments = new ArrayList<>();
    }

    // Getter và Setter / Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public boolean isLiked() { return isLiked; }
    public void toggleLike() { this.isLiked = !this.isLiked; }

    public List<String> getComments() { return comments; }
    public void addComment(String comment) { this.comments.add(comment); }
}
