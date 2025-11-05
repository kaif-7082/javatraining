package com.example.firstjobapp.companies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);


    @Query("SELECT c FROM Company c " +
            "WHERE c.name LIKE %:query% " +
            "OR c.description LIKE %:query% " +
            "OR c.ceo LIKE %:query%")
    List<Company> searchCompanies(@Param("query") String query);
}
