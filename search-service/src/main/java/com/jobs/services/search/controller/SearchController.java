package com.jobs.services.search.controller;

import com.jobs.services.search.dto.Filter;
import com.jobs.services.search.dto.Page;
import com.jobs.services.search.interfaces.SearchService;
import com.jobs.services.search.model.Job;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.jobs.services.search.constant.SearchConstant.*;


@RestController
@RequestMapping("/v1/search")
@Validated
@Slf4j
public class SearchController {
    @Autowired
    SearchService jobService;

    @PostMapping("/jobs")
    public Page<Job> searchJobs(@RequestParam(required = true, defaultValue = DEFAULT_PAGE) int pageNum,
                                @RequestParam(required = true, defaultValue = DEFAULT_SIZE) @Max(value = MAX_PAGE_SIZE, message = "Page size exceeds limit") int size,
                                @RequestParam(required = true) String query,
                                @RequestHeader String correlationId,
                                @RequestBody @Valid Filter filters) {
        log.info("Execute search job request for correlationId: {}",correlationId);
        return jobService.search(query, filters, pageNum, size);
    }
}
