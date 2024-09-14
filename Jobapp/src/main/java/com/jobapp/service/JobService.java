package com.jobapp.service;



import com.jobapp.dto.JobDTO;
import com.jobapp.model.Job;

import java.util.List;

public interface JobService {

    //create job
    public Job saveJob(Job job);

    //get job list
   // public List<Job> getJobList();
    public List<JobDTO> getJobList();

    //get single list
   // public Job getJobById(Long id);
    public JobDTO getJobById(Long id);

    //update job
    public boolean updateJob(Long id, Job updateJob);

    //delete job
    public boolean deleteJob(Long id);



}
