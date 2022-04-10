package ru.hh.school.DTO.Vacancy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.DTO.AreaDTO;
import ru.hh.school.DTO.Employer.EmployerDTO;
import ru.hh.school.DTO.Employer.ShortEmployerDTO;
import ru.hh.school.DTO.SalaryDTO;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyDTO {
    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @NotNull
    private AreaDTO area;

    private SalaryDTO salary;

    @JsonProperty("employer")
    @NotNull
    private ShortEmployerDTO employer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaDTO getArea() {
        return area;
    }

    public void setArea(AreaDTO area) {
        this.area = area;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(String createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ROOT);
        this.createdAt = LocalDateTime.parse(createdAt, formatter);

    }

    public ShortEmployerDTO getEmployer() {
        return employer;
    }

    public void setEmployer(ShortEmployerDTO employer) {
        this.employer = employer;
    }

    public SalaryDTO getSalary() {
        return salary;
    }

    public void setSalary(SalaryDTO salary) {
        this.salary = salary;
    }
}
