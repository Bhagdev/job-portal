package com.jobs.services.search.interfaces;

import com.jobs.services.search.model.Job;
import com.jobs.services.search.dto.Filter;
import com.jobs.services.search.dto.Page;

public interface SearchService {

    /**
     *
     * @param keyword
     * @param filters
     * @param page
     * @param size
     * @return return paginated response for given search criteria
     */
    Page<Job> search(String keyword, Filter filters, int page, int size);
}
