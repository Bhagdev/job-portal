package com.jobs.services.recommendation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Document(indexName = Job.INDEX)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job implements Serializable {
    public static final String INDEX = "job";
    @Id
    private long id;
    @Field(type = FieldType.Wildcard)
    private String title;
    @Field(type = FieldType.Wildcard)
    private String role;
    private String jobType;
    private Company company;
    @Field(type = FieldType.Date, format = {}, pattern = "uuuu-MM-dd")
    private LocalDate postedOn;
    private String postedBy;
    @Field(type = FieldType.Keyword)
    private List<String> skills;
    private String description;
    private String logoUrl;
    @Field(type = FieldType.Nested, includeInParent = true)
    private Range experience;
    @Field(type = FieldType.Nested, includeInParent = true)
    private Range salary;
    private int rating;
    @Field(type = FieldType.Keyword)
    private List<String> tags;
    @Field(type = FieldType.Nested, includeInParent = true)
    private Location location;
}
