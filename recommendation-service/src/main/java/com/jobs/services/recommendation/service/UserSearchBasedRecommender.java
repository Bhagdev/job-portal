package com.jobs.services.recommendation.service;

import com.jobs.services.recommendation.dto.Page;
import com.jobs.services.recommendation.interfaces.Recommender;
import com.jobs.services.recommendation.model.Job;
import org.springframework.stereotype.Service;

@Service
public class UserSearchBasedRecommender implements Recommender {
    @Override
    public Page<Job> recommend(String userId, int page, int size) {
        return null;
    }
}
