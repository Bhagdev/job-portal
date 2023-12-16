package com.jobs.services.recommendation.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class Range implements Serializable {
    private int min;
    private int max;
}
