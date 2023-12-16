# Job Search Portal

## Requirements:
1. The system should allow users to create profiles, search for jobs, and apply for jobs.
2. The system should have a recommendation engine that suggests jobs based on a user's profile and search history.
3. The system should have a messaging system that allows users to communicate with recruiters and hiring managers.
4. The system should have a salary comparison feature that allows users to compare salaries for different jobs.

## Architecture

![image](https://github.com/Bhagdev/job-portal/blob/main/job_portal-HLD.drawio.png)

 Microservices-based system consists of the following modules:
- **gateway-service** - a module that Spring Cloud Netflix Zuul for running Spring Boot application that acts as a proxy/gateway.
- **discovery-service** - a module that uses Spring Cloud Netflix Eureka as an embedded discovery server.
- **search-service** - microservice to perform searches on elastic search for jobs data.
- **recommendation-service** - microservice to recommend jobs for user based on profile and search history



