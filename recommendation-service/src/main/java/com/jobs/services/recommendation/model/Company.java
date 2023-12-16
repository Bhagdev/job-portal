package com.jobs.services.recommendation.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Company implements Serializable {
    private int companyId;
    private String companyName;
}
