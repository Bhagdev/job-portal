package com.jobs.services.search.service;

import com.jobs.services.search.dto.Filter;
import com.jobs.services.search.dto.Page;
import com.jobs.services.search.model.Job;
import org.junit.jupiter.api.Assertions;
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
    public void searchJobs() {
        Filter filter = Filter.builder().location("mumbai").build();
        SearchHits hits = mock(SearchHits.class);
        when(hits.getTotalHits()).thenReturn(1L);
        when(hits.getSearchHits()).thenReturn(Collections.emptyList());
        when(esTemplate.search(any(Query.class),any())).thenReturn(hits);
        Page<Job> jobs = jobSearchService.search("java", filter, 0, 10);
        Assert.notNull(jobs,"");
        assertThat(1L, equalTo(hits.getTotalHits()));
    }

    @Test
    public void searchJobsThrowsException() {
        Filter filter = Filter.builder().location("mumbai").build();
        SearchHits hits = mock(SearchHits.class);
        when(hits.getSearchHits()).thenReturn(null);
        when(esTemplate.search(any(Query.class),any())).thenReturn(hits);
        Assertions.assertThrowsExactly(NullPointerException.class,() -> jobSearchService.search("java", filter, 0, 10));
    }
}
