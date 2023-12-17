package com.jobs.services.recommendation.repository;

import com.jobs.services.recommendation.model.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class UserRepository {
    public User getUserById(String userId) {
        return User.builder().userId(userId)
                .userName("TestUser")
                .tags(Arrays.asList("sql", "java"))
                .build();
    }
}
