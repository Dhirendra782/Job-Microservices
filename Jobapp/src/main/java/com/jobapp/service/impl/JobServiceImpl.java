package com.jobapp.service.impl;


import com.jobapp.client.CompanyClient;
import com.jobapp.client.ReviewClient;
import com.jobapp.dto.JobDTO;
import com.jobapp.external.Company;
import com.jobapp.external.Review;
import com.jobapp.mapper.JobMapper;
import com.jobapp.model.Job;
import com.jobapp.repository.JobRepository;
import com.jobapp.service.JobService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {


    private JobRepository jobRepository;

    private CompanyClient companyClient;

    private ReviewClient reviewClient;

    @Autowired
        RestTemplate restTemplate;


    //Create job
    @Override
    public Job saveJob(Job job) {
        Job saveJob = jobRepository.save(job);
        return saveJob;
    }

    //Get All job
    //@CircuitBreaker(name="companyBreaker", fallbackMethod = "companyBreakerFallback")
    //@Retry(name ="companyBreaker",fallbackMethod = "companyBreakerFallback")
    //@RateLimiter(name ="companyBreaker")
    @Override
    public List<JobDTO> getJobList() {
        List<Job> getList = jobRepository.findAll();
        List<JobDTO> jobWithCompanyDTOS = new ArrayList<>();

        return getList.stream().map(this::converrtToDto).collect(Collectors.toList());
    }

    //ConvertToDto
    private JobDTO converrtToDto(Job job) {

        //This is using figen client CompanyApp
        Company company = companyClient.getCompany(job.getCompanyId());
        //This is using figen client ReviewApp
        List<Review> review = reviewClient.getReview(job.getCompanyId());
        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDto(job,company,review);

        return jobDTO;
    }

    @Override
    public JobDTO getJobById(Long id) {
        //Job getById = jobRepository.findById(id).orElse(null);
        Job getById = jobRepository.findById(id).orElseThrow();
        return converrtToDto(getById);
    }

    //update job
    @Override
    public boolean updateJob(Long id, Job updateJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if(optionalJob.isPresent()){
            Job job = optionalJob.get();
            job.setTitle(updateJob.getTitle());
            job.setDescription(updateJob.getDescription());
            job.setMinSalary(updateJob.getMinSalary());
            job.setMaxSalary(updateJob.getMaxSalary());
            job.setLocation(updateJob.getLocation());
            job.setCompanyId(updateJob.getCompanyId());
            jobRepository.save(job);
            return true;
        }
        else {
            return false;
        }

    }

    //delete job by id
    @Override
    public boolean deleteJob(Long id) {
        if(jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }

    }

}
