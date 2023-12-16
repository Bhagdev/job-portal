package com.jobs.services.recommendation.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Currency;

@Data
@Builder
public class Salary implements Serializable {
    private double salary;
    private Currency currency;
}
