package com.jobs.services.search.service;

import com.jobs.services.search.builder.QueryBuilder;
import com.jobs.services.search.interfaces.SearchService;
import com.jobs.services.search.model.Job;
import com.jobs.services.search.repository.JobRepository;
import com.jobs.services.search.dto.Filter;
import com.jobs.services.search.dto.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class JobSearchService implements SearchService {

    @Autowired
    final ElasticsearchOperations esTemplate;

    @Override
    public Page<Job> search(String keyword, Filter filters, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        SearchHits<Job> searchHits = searchHits(keyword, filters, pageable);
        List<Job> jobs = searchHits
                .getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
        return Page.<Job>builder().totalCount(searchHits.getTotalHits())
                .pageSize(jobs.size())
                .pageNum(page).result(jobs).build();
    }

    private SearchHits<Job> searchHits(String keyword, Filter filters,Pageable pageable) {
        Query searchQuery = NativeQuery.builder()
                .withQuery(QueryBuilder.buildSearchQuery(keyword, filters))
                .withPageable(pageable)
                .build();
        return esTemplate.search(searchQuery,Job.class);
    }


}
