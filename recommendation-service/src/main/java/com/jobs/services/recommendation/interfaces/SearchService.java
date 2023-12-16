package com.jobs.services.recommendation.interfaces;

import com.jobs.services.recommendation.dto.Filter;
import com.jobs.services.recommendation.dto.Page;
import com.jobs.services.recommendation.model.Job;

import java.util.List;

public interface SearchService {
    Page<Job> findMoreLikeThis(List<String> keyWords,List<String> fields, Filter filter, int page, int size);
}
