package com.jobs.services.recommendation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class Page<T>{
    private long totalCount;
    private int pageNum;
    private long pageSize;
    private List<T> result;
}
