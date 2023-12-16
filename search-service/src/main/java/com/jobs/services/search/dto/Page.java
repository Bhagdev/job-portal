package com.jobs.services.search.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Builder
public class Page<T>{
    private long totalCount;
    private int pageNum;
    private long pageSize;
    private List<T> result;
}
