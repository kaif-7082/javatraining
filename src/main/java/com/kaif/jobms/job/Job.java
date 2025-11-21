package com.kaif.jobms.job;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    // Changed to Integer to match monolith and support salary-based queries
    private Integer minSalary;
    private Integer maxSalary;
    private String location;
    private Long companyId;

    public Job(Long id, String title, String description, Integer minSalary, Integer maxSalary, String location, Long companyId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        this.companyId = companyId;
    }
}