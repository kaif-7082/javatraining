package com.kaif.jobms.job.mapper;

import com.kaif.jobms.job.Job;
import com.kaif.jobms.job.dto.JobDTO;
import com.kaif.jobms.job.external.Company;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO(Job job, Company company){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);

        return jobDTO;
    }
}