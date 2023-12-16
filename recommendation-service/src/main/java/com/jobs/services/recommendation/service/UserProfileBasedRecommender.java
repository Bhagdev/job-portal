package com.jobs.services.recommendation.service;


import com.jobs.services.recommendation.dto.Filter;
import com.jobs.services.recommendation.dto.Page;
import com.jobs.services.recommendation.interfaces.Recommender;
import com.jobs.services.recommendation.model.Job;
import com.jobs.services.recommendation.model.User;
import com.jobs.services.recommendation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.jobs.services.recommendation.constant.QueryConstant.JOB_SEARCH_FIELDS;

@Service
public class UserProfileBasedRecommender implements Recommender {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobSearchService searchService;

    public Page<Job> recommend(String userId, int page, int size) {
        User user = userRepository.getUserById(userId);
        Filter filter = Filter.builder().location(user.getCurrentLocation()).build();
        return searchService.findMoreLikeThis(user.getTags(), JOB_SEARCH_FIELDS, filter, page, size);
    }

}
