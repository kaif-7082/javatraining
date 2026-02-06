package com.example.firstjobapp.companies;

import com.example.firstjobapp.companies.dto.companyRequestDto;
import com.example.firstjobapp.companies.dto.companyResponseDto;
import com.example.firstjobapp.job.Job;
import jakarta.validation.Valid; // ADD
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping
    public ResponseEntity<List<companyResponseDto>> getAllCompanies() {
        log.info("GET /companies - Request to get all companies");
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                @Valid @RequestBody companyRequestDto companyDto) {
        log.info("PUT /companies/{} - Request to update company", id);
        boolean updated = companyService.updateCompany(companyDto, id);
        if (updated) {
            return new ResponseEntity<>("Company updated", HttpStatus.OK);
        }
        log.warn("PUT /companies/{} - Company not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<String> createCompany(@Valid @RequestBody companyRequestDto companyDto) {
        log.info("POST /companies - Request to create new company: {}", companyDto.getName());
        companyService.createCompany(companyDto);
        log.info("POST /companies - Company created successfully: {}", companyDto.getName());
        return new ResponseEntity<>("Company created", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        log.info("DELETE /companies/{} - Request to delete company", id);
        boolean deleted = companyService.deleteCompanyById(id);
        if (deleted) {
            return new ResponseEntity<>("Company deleted", HttpStatus.OK);
        }
        log.warn("DELETE /companies/{} - Company not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        log.info("GET /companies/{} - Request to get company by id", id);
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        log.warn("GET /companies/{} - Company not found", id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/sorted/{field}")
    public ResponseEntity<List<Company>> findSortedCompanies(@PathVariable String field) {
        log.info("GET /companies/sorted/{} - Request to get sorted companies", field);
        List<Company> sortedCompanies = companyService.findCompaniesWithSorting(field);
        return ResponseEntity.ok(sortedCompanies);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Company> findCompanyByName(@PathVariable String name) {
        log.info("GET /companies/name/{} - Request to get company by name", name);
        Company company = companyService.findCompanyByName(name);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        log.warn("GET /companies/name/{} - Company not found", name);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Company>> searchCompanies(@RequestParam String query) {
        log.info("GET /companies/search - Request to search companies with query: {}", query);
        List<Company> companies = companyService.searchCompanies(query);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/filterByYear/{year}")
    public ResponseEntity<List<Company>> getCompaniesByYear(@PathVariable Integer year) {
        log.info("GET /companies/filterByYear/{} - Request to filter companies by year", year);
        List<Company> companies = companyService.findCompaniesByFoundedYear(year);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/pagination/{page}/{pageSize}")
    public ResponseEntity<Page<Company>> getJobsWithPagination(@PathVariable int page, @PathVariable int pageSize) {
        log.info("GET /companies/pagination/{}/{} - Request to get paginated companies", page, pageSize);
        Page<Company> companies = companyService.findCompanyWithPagination(page, pageSize);
        return ResponseEntity.ok(companies);
    }
}