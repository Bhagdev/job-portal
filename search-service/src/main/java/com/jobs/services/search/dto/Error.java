package com.jobs.services.search.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Error {
    private String msg;
    private int code;
}
