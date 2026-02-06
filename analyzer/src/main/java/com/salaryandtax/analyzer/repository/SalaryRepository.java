package com.salaryandtax.analyzer.repository;

import com.salaryandtax.analyzer.model.SalaryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<SalaryRecord, Long> {

}