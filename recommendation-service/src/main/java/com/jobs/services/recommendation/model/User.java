package com.jobs.services.recommendation.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@Builder
public class User implements Serializable {
    private String userId;
    private String userName;
    private String email;
    private List<Skill> skills;
    private List<UserExperience> experiences;
    private Salary salary;
    private List<String> tags;

    public String getCurrentLocation() {
        return "Mumbai";
    }
}
