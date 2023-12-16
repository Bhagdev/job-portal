package com.jobs.services.recommendation.model;


import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class UserExperience implements Serializable {
    private Company company;
    private boolean isCurrent;
    private LocalDate start;
    private LocalDate end;
    private Location location;
}
