package com.salaryandtax.analyzer.controller;

import com.salaryandtax.analyzer.model.SalaryRecord;
import com.salaryandtax.analyzer.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

  
    @PostMapping("/add")
    public SalaryRecord addSalaryRecord(@RequestBody SalaryRecord record) {
        return salaryService.saveRecord(record);
    }


    @GetMapping("/all-details")
    public Map<String, Object> getAllDetails(@RequestParam(defaultValue = "1000") Double threshold) {
        return salaryService.getAllAnalysis(threshold);
    }


}