package com.example.GoalMaster.request;

import java.util.List;

public class GoalCreateUpdateRequestDTO {
    private Long idGoals;
    private String title;
    private String status;
    private String description;
    private String start_date;
    private String end_date;
    private Long userId;  // sửa tên biến thành userId chuẩn camelCase
    private List<String> goalProgressList;  // thêm danh sách nhiệm vụ (task list)

    public Long getIdGoals() {
        return idGoals;
    }

    public void setIdGoals(Long idGoals) {
        this.idGoals = idGoals;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getGoalProgressList() {
        return goalProgressList;
    }

    public void setGoalProgressList(List<String> goalProgressList) {
        this.goalProgressList = goalProgressList;
    }
}
