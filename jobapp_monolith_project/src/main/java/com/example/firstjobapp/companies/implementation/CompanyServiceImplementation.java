package com.example.firstjobapp.companies.implementation;

import com.example.firstjobapp.companies.Company;
import com.example.firstjobapp.companies.CompanyRepository;
import com.example.firstjobapp.companies.CompanyService;
import com.example.firstjobapp.companies.dto.companyRequestDto;
import com.example.firstjobapp.companies.dto.companyResponseDto;
import com.example.firstjobapp.job.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CompanyServiceImplementation implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImplementation(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public List<companyResponseDto> getAllCompanies() {
        log.info("Executing getAllCompanies");
        List<Company> companies = companyRepository.findAll();
        List<companyResponseDto> responseDtos = new ArrayList<>();

        for (Company company : companies) {
            companyResponseDto dto = new companyResponseDto();
            dto.setId(company.getId());
            dto.setName(company.getName());
            dto.setDescription(company.getDescription());
            dto.setCeo(company.getCeo());
            dto.setFoundedYear(company.getFoundedYear());

            responseDtos.add(dto);
        }
        log.info("Found {} companies", responseDtos.size());
        return responseDtos;
    }

    @Override
    public boolean updateCompany(companyRequestDto companyDto, Long id) {
        log.info("Attempting to update company with id: {}", id);
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            company.setName(companyDto.getName());
            company.setDescription(companyDto.getDescription());
            company.setCeo(companyDto.getCeo());
            company.setFoundedYear(companyDto.getFoundedYear());

            companyRepository.save(company);
            log.info("Successfully updated company with id: {}", id);
            return true;
        }
        log.warn("Failed to update. Company not found with id: {}", id);
        return false;
    }


    @Override
    public void createCompany(companyRequestDto companyDto) {
        log.info("Creating new company: {}", companyDto.getName());
        Company company = new Company();
        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setCeo(companyDto.getCeo());
        company.setFoundedYear(companyDto.getFoundedYear());
        

        companyRepository.save(company);
        log.info("Successfully created company with id: {}", company.getId());
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        log.info("Attempting to delete company with id: {}", id);
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            log.info("Successfully deleted company with id: {}", id);
            return true;
        }
        log.warn("Failed to delete. Company not found with id: {}", id);
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        log.info("Finding company by id: {}", id);
        return companyRepository.findById(id).orElse(null);
    }


    @Override
    public List<Company> findCompaniesWithSorting(String field) {
        log.info("Finding companies with sorting on field: {}", field);
        return companyRepository.findAll(Sort.by(Sort.Direction.DESC, field));
    }

    @Override
    public Company findCompanyByName(String name) {
        log.info("Finding company by name: {}", name);
        return companyRepository.findByName(name);
    }

    @Override
    public List<Company> searchCompanies(String query) {
        log.info("Searching for companies with query: {}", query);
        return companyRepository.searchCompanies(query);
    }

    @Override
    public List<Company> findCompaniesByFoundedYear(Integer year) {
        log.info("Finding companies by founded year: {}", year);
        return companyRepository.findByFoundedYear(year);
    }

    @Override
    public Page<Company> findCompanyWithPagination(int page, int pageSize) {
        log.info("Finding companies with pagination - page: {}, pageSize: {}", page, pageSize);
        Page<Company> companies=companyRepository.findAll(PageRequest.of(page, pageSize));
        return companies;
    }
}