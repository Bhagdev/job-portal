package com.jobs.services.search.load;


import com.github.javafaker.Faker;
import com.jobs.services.search.model.Company;
import com.jobs.services.search.model.Job;
import com.jobs.services.search.model.Location;
import com.jobs.services.search.model.Range;
import com.jobs.services.search.repository.JobRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class BulkService {
    private Faker faker = new Faker(new Locale("en-IND"));

    @Autowired
    private JobRepository jobRepository;

    public void loadJobs(int start,int end){
        List<Job> jobs = getFakeJobList(start,end);
        jobRepository.saveAll(jobs);
    }

    private List<Job> getFakeJobList(int start, int end) {
        List<Job> jobs = new ArrayList<>();
        for (int i = start; i < end; i++) {
            jobs.add(Job.builder().id(i)
                    .location(Location.builder()
                            .state(faker.address().state())
                            .city(faker.address().city())
                            .country(faker.address().country()).build())
                    .title(faker.job().title())
                    .role(faker.job().position())
                    .postedBy(faker.name().fullName())
                    .logoUrl(faker.company().logo())
                    .postedOn(LocalDate.now())
                    .salary(Range.builder()
                            .min(faker.number().numberBetween(100, 1000))
                            .max(faker.number().numberBetween(2000, 10000)).build())
                    .skills(Arrays.asList(StringUtils.splitPreserveAllTokens(faker.job().keySkills(), ",")))
                    .tags(Arrays.asList(StringUtils.splitPreserveAllTokens(faker.job().keySkills(), ",")))
                    .experience(Range.builder().min(5).max(10).build())
                    .company(Company.builder().companyName(faker.company().name()).companyId(i).build())
                    .build());

        }
        return jobs;
    }
}
