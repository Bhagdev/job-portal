package com.jobs.services.recommendation.service;

import com.jobs.services.recommendation.builder.QueryBuilder;
import com.jobs.services.recommendation.dto.Filter;
import com.jobs.services.recommendation.dto.Page;
import com.jobs.services.recommendation.interfaces.SearchService;
import com.jobs.services.recommendation.model.Job;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobSearchService implements SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobSearchService.class);

    @Autowired
    final ElasticsearchOperations esTemplate;
    @Override
    public Page<Job> findMoreLikeThis(List<String> keyWords,List<String> fields,Filter filter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        SearchHits<Job> searchHits = searchHits(keyWords, filter,fields, pageable);
        List<Job> jobs = searchHits
                .getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
        return Page.<Job>builder().totalCount(searchHits.getTotalHits())
                .pageSize(jobs.size())
                .pageNum(page).result(jobs).build();
    }

    public SearchHits<Job> searchHits(List<String> criteria, Filter filters,List<String> fields,Pageable pageable) {
        Query searchQuery = NativeQuery.builder()
                .withQuery(QueryBuilder.buildLikeQuery(criteria, filters,fields))
                .withPageable(pageable)
                .build();
        return esTemplate.search(searchQuery,Job.class);
    }


}
