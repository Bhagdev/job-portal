package com.jobs.services.search.builder;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.jobs.services.search.dto.Filter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.jobs.services.search.constant.QueryConstant.*;
import static java.util.Objects.nonNull;

public class QueryBuilder {
    public static co.elastic.clients.elasticsearch._types.query_dsl.Query buildSearchQuery(String keyword, Filter filters) {
        List<Query> queries = new ArrayList<>();
        if (nonNull(keyword)) {
            queries.add(QueryBuilders.queryString().query(keyword).build()._toQuery());
        }

        if (nonNull(filters)) {
            if (StringUtils.isNotBlank(filters.getLocation())) {
                queries.add(QueryBuilders.multiMatch().query(filters.getLocation()).fields(CITY, STATE, COUNTRY).build()._toQuery());
            }

        }
        return QueryBuilders.bool().must(queries).build()._toQuery();

    }
}
