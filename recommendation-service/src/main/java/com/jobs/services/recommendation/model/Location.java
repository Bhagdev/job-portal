package com.jobs.services.recommendation.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Location implements Serializable {
    private long id;
    private String city;
    private String country;
    private String state;
    private double latitude;
    private double longitude;
}
