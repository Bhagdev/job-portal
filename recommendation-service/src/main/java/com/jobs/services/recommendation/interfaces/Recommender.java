package com.jobs.services.recommendation.interfaces;

import com.jobs.services.recommendation.dto.Page;
import com.jobs.services.recommendation.model.Job;

import java.util.List;

public interface Recommender {
    /**
     * Generates recommendation for given user based on profile
     * @param userId
     * @param page
     * @param size
     * @return recommended Jobs for given page
     */
    Page<Job> recommend(String userId, int page, int size);
}
