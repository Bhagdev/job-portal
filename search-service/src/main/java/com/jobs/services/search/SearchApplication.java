package com.jobs.services.search;

import com.jobs.services.search.load.BulkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SearchApplication {

	@Autowired
	private BulkService bulkService;

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void loadJobs() {
		System.out.println("Load Jobs");
		bulkService.loadJobs(1,100);
		System.out.println("Job load complete");
	}
}
