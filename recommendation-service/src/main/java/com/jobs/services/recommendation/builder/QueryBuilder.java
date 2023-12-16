package com.jobs.services.recommendation.builder;

import co.elastic.clients.elasticsearch._types.query_dsl.Like;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.jobs.services.recommendation.dto.Filter;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.jobs.services.recommendation.constant.QueryConstant.*;
import static java.util.Objects.nonNull;

public class QueryBuilder {
    public static Query buildLikeQuery(List<String> keywords, Filter filters, List<String> fields) {
        List<Query> queries = new ArrayList<>();
        if (CollectionUtils.isEmpty(keywords)) {
            queries.add(QueryBuilders.moreLikeThis()
                    .like(buildLike(keywords))
                    .fields(fields)
                    .minTermFreq(MIN_TERM_FREQ)
                    .maxQueryTerms(MAX_TERM_FREQ)
                    .build()
                    ._toQuery());
        }

        if (nonNull(filters)) {
            if (StringUtils.isNotBlank(filters.getLocation())) {
                queries.add(QueryBuilders.multiMatch().query(filters.getLocation()).fields(CITY, STATE, COUNTRY).build()._toQuery());
            }

        }
        return QueryBuilders.bool().must(queries).build()._toQuery();

    }

    public static List<Like> buildLike(List<String> likes) {
        return likes.stream().map(like -> new Like.Builder()
                        .text(like)
                        .build())
                .collect(Collectors.toList());
    }
}
