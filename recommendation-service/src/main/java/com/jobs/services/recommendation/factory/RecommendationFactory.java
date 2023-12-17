package com.jobs.services.recommendation.factory;

import com.jobs.services.recommendation.interfaces.Recommender;
import com.jobs.services.recommendation.model.RecommendationType;
import com.jobs.services.recommendation.service.UserProfileBasedRecommender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RecommendationFactory {

    @Autowired
    UserProfileBasedRecommender profileRecommender;

    public Recommender getRecommender(RecommendationType type) {
        Recommender recommend = null;
        switch (type) {
            case PROFILE: {
                recommend = profileRecommender;
                break;
            }
            default: {
                throw new NotImplementedException("Implementation not supported for type:" + type.name());
            }
        }

        return recommend;
    }
}
