package com.salaryandtax.analyzer.service;

import com.salaryandtax.analyzer.model.SalaryRecord;
import com.salaryandtax.analyzer.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalaryService {

    @Autowired
    private SalaryRepository repository;

    public SalaryRecord saveRecord(SalaryRecord record) {
        return repository.save(record);
    }


    public Map<String, Object> getAllAnalysis(Double threshold) {

        List<SalaryRecord> allData = repository.findAll();


        Map<String, Object> report = new LinkedHashMap<>();

        //  Totals per Employee
        Map<String, Double> totals = allData.stream()
                .collect(Collectors.groupingBy(SalaryRecord::getEmployeeId,
                        Collectors.summingDouble(SalaryRecord::getGrossSalary)));

      //  Tax per Month
        Map<String, Double> taxes = allData.stream()
                .collect(Collectors.groupingBy(SalaryRecord::getMonth,
                        Collectors.summingDouble(SalaryRecord::getTaxAmount)));

    //  High Tax IDs
        List<String> highTax = allData.stream()
                .filter(r -> r.getTaxAmount() > threshold)
                .map(SalaryRecord::getEmployeeId)
                .distinct()
                .collect(Collectors.toList());

        //  Average per Month
        Map<String, Double> averages = allData.stream()
                .collect(Collectors.groupingBy(SalaryRecord::getMonth,
                        Collectors.averagingDouble(SalaryRecord::getGrossSalary)));

        //  Sorted IDs (using the 'totals' map created in step 3)
        List<String> sortedIds = totals.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Assemble everything
        report.put("totalSalaryPerEmployee", totals);
        report.put("totalTaxPerMonth", taxes);
        report.put("highTaxEmployees", highTax);
        report.put("averageSalaryPerMonth", averages);
        report.put("employeesSortedByYearlySalary", sortedIds);

        return report;
    }
}