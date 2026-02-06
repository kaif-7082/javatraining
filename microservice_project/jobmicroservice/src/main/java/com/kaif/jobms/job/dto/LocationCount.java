package com.kaif.jobms.job.dto;

// This DTO was added from the monolith project
public class LocationCount {
    String location;
    Long count;

    public LocationCount(String location, Long count) {
        this.location = location;
        this.count = count;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}