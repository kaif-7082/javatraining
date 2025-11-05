package com.example.firstjobapp.companies;

import com.example.firstjobapp.companies.dto.companyRequestDto;  // ADD
import com.example.firstjobapp.companies.dto.companyResponseDto; // ADD

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
}