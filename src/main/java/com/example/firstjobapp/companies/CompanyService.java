package com.example.firstjobapp.companies;

import com.example.firstjobapp.companies.dto.companyRequestDto;
import com.example.firstjobapp.companies.dto.companyResponseDto;
import com.example.firstjobapp.job.Job;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {


    List<companyResponseDto> getAllCompanies();


    boolean updateCompany(companyRequestDto company,Long id);


    void createCompany(companyRequestDto company);

    boolean deleteCompanyById(Long id);

    Company getCompanyById(Long id);


    List<Company> findCompaniesWithSorting(String field);
    Company findCompanyByName(String name);
    List<Company> searchCompanies(String query);
    List<Company> findCompaniesByFoundedYear(Integer year);
    Page<Company> findCompanyWithPagination(int page, int pageSize);
}