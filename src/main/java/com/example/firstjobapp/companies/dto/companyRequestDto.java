package com.example.firstjobapp.companies.dto;

import jakarta.validation.constraints.NotEmpty;

public class companyRequestDto {

    @NotEmpty(message = "Company name cannot be empty")
    private String name;

    @NotEmpty(message = "Company description cannot be empty")
    private String description;

    @NotEmpty(message = "CEO name cannot be empty")
    private String ceo;

    private Integer foundedYear;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }
}