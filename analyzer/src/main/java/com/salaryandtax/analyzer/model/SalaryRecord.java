package com.salaryandtax.analyzer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "salary_records")
@Data
public class SalaryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee ID is mandatory")
    private String employeeId;
    private String month;
    private Double grossSalary;
    private Double taxAmount;
}