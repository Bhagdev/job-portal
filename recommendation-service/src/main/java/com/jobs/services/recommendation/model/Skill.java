package com.jobs.services.recommendation.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Skill implements Serializable {
    private int id;
    private String name;
}
