package com.jobs.services.recommendation.service;

import com.jobs.services.recommendation.dto.Filter;
import com.jobs.services.recommendation.dto.Page;
import com.jobs.services.recommendation.model.Job;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobSearchServiceTest {
    @Mock
    private ElasticsearchOperations esTemplate;

    @InjectMocks
    private JobSearchService jobSearchService;

    @Test
    public void findMoreLikeThisJobs() {
        Filter filter = Filter.builder().location("mumbai").build();
        SearchHits hits = mock(SearchHits.class);
        when(hits.getTotalHits()).thenReturn(1L);
        when(hits.getSearchHits()).thenReturn(Collections.emptyList());
        when(esTemplate.search(any(Query.class),any())).thenReturn(hits);
        Page<Job> jobs = jobSearchService.findMoreLikeThis(List.of("java"), List.of("skill"),filter,0,10);
        Assert.notNull(jobs,"");
        assertThat(1L, equalTo(hits.getTotalHits()));
    }

}
