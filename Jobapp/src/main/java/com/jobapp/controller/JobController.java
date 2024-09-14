package com.jobapp.controller;

import com.jobapp.dto.JobDTO;
import com.jobapp.model.Job;
import com.jobapp.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@AllArgsConstructor
public class JobController {


    private JobService jobService;


    //Create Job
    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        Job saveJob = jobService.saveJob(job);
        return new ResponseEntity<>(saveJob, HttpStatus.CREATED);
    }

    //get all job list
    @GetMapping
    public ResponseEntity<List<JobDTO>> getJobList() {
        List<JobDTO> getJobList = jobService.getJobList();
        return new ResponseEntity<>(getJobList,HttpStatus.OK);
    }

    //Get single record by job id
    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        JobDTO getJobById = jobService.getJobById(id);
        if(getJobById !=null){
            return new ResponseEntity<>(getJobById,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(getJobById,HttpStatus.NOT_FOUND);
        }
    }

    //Update job by id
    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updateJob) {
        boolean isUpdate = jobService.updateJob(id, updateJob);
        if(isUpdate){
            return new ResponseEntity<>("Job Update Successfully ",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Job Not Found",HttpStatus.NOT_FOUND);
        }

    }

    //delete job
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean isUpdate = jobService.deleteJob(id);
        if(isUpdate) {
            return new ResponseEntity<>("Job Delete Successfully ",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Job Not Found",HttpStatus.NOT_FOUND);
        }

    }

}
