package com.jobapp.client;

import com.jobapp.external.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="REVIEWAPP")
public interface ReviewClient {

    @GetMapping("/reviews")
    List<Review> getReview(@RequestParam("companyId") Long companyId);

}

























