package com.assignment2.messagequeue.dto;

public class UserActivityEvent {
    private String userId;
    private String type; // e.g., "LOGIN_SUCCESS", "LOGIN_FAILURE"

    public UserActivityEvent() {
    }

    public UserActivityEvent(String userId, String type) {
        this.userId = userId;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserActivityEvent{" +
                "userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}