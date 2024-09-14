package com.jobapp.client;


import com.jobapp.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="COMPANYAPP-SERVICE")
public interface CompanyClient {

    @GetMapping("/companys/{id}")
    Company getCompany(@PathVariable("id") Long Id);

}
