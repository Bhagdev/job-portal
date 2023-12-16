package com.jobs.services.search.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class Company implements Serializable {
    private int companyId;
    private String companyName;
}
