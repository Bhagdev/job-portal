package com.jobs.services.recommendation.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Filter {
    private String location;
    private String skill;
    private String companyName;
    private String role;
    private String yrsOfExp;
}
