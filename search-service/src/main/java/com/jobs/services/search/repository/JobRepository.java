package com.jobs.services.search.repository;

import com.jobs.services.search.model.Job;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends ElasticsearchRepository<Job, Long> {
}
