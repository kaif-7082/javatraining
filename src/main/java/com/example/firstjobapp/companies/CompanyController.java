package com.example.firstjobapp.companies;

import com.example.firstjobapp.companies.dto.companyRequestDto;
import com.example.firstjobapp.companies.dto.companyResponseDto;
import com.example.firstjobapp.job.Job;
import jakarta.validation.Valid; // ADD
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping
    public ResponseEntity<List<companyResponseDto>> getAllCompanies() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                @Valid @RequestBody companyRequestDto companyDto) {
        boolean updated = companyService.updateCompany(companyDto, id);
        if (updated) {
            return new ResponseEntity<>("Company updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<String> createCompany(@Valid @RequestBody companyRequestDto companyDto) {
        companyService.createCompany(companyDto);
        return new ResponseEntity<>("Company created", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompanyById(id);
        if (deleted) {
            return new ResponseEntity<>("Company deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/sorted/{field}")
    public ResponseEntity<List<Company>> findSortedCompanies(@PathVariable String field) {
        List<Company> sortedCompanies = companyService.findCompaniesWithSorting(field);
        return ResponseEntity.ok(sortedCompanies);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Company> findCompanyByName(@PathVariable String name) {
        Company company = companyService.findCompanyByName(name);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Company>> searchCompanies(@RequestParam String query) {
        List<Company> companies = companyService.searchCompanies(query);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/filterByYear/{year}")
    public ResponseEntity<List<Company>> getCompaniesByYear(@PathVariable Integer year) {
        List<Company> companies = companyService.findCompaniesByFoundedYear(year);
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/pagination/{page}/{pageSize}")
    public ResponseEntity<Page<Company>> getJobsWithPagination(@PathVariable int page, @PathVariable int pageSize) {
        Page<Company> companies = companyService.findCompanyWithPagination(page, pageSize);
        return ResponseEntity.ok(companies);
    }
}