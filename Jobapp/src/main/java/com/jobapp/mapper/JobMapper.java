package com.jobapp.mapper;


import com.jobapp.dto.JobDTO;
import com.jobapp.external.Company;
import com.jobapp.external.Review;
import com.jobapp.model.Job;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDto(Job job, Company company,List<Review> reviews) {
        JobDTO jobDTO = new JobDTO();
            jobDTO.setId(job.getId());
            jobDTO.setTitle(job.getTitle());
            jobDTO.setDescription(job.getDescription());
            jobDTO.setMinSalary(job.getMinSalary());
            jobDTO.setMaxSalary(job.getMaxSalary());
            jobDTO.setLocation(job.getLocation());
            jobDTO.setCompany(company);
            jobDTO.setReviews(reviews);

        return jobDTO;

    }

}
















