package com.jobs.services.recommendation.controller;

import com.jobs.services.recommendation.dto.Page;
import com.jobs.services.recommendation.factory.RecommendationFactory;
import com.jobs.services.recommendation.model.Job;
import com.jobs.services.recommendation.model.RecommendationType;
import jakarta.validation.constraints.Max;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.jobs.services.recommendation.constant.SearchConstant.*;

@RestController
@RequestMapping("/v1/recommendations")
@Validated
@Slf4j
public class RecommendationController {

    @Autowired
    RecommendationFactory recommendationFactory;

    @GetMapping
    public Page<Job> recommend(@RequestParam(defaultValue = DEFAULT_PAGE) int pageNum,
                               @RequestParam(defaultValue = DEFAULT_SIZE) @Max(value = MAX_PAGE_SIZE, message = "Page size exceeds limit") int size,
                               @RequestParam(required = true) RecommendationType type,
                               @RequestHeader String correlationId,
                               @RequestHeader String userId) {
        log.info("Execute recommend request for user:{} correlationId:{}",userId,correlationId);
        return recommendationFactory.getRecommender(type)
                .recommend(userId, pageNum, size);
    }

}
