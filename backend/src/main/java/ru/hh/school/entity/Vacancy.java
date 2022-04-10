package ru.hh.school.entity;

import ru.hh.school.DTO.Employer.EmployerDTO;
import ru.hh.school.DTO.Vacancy.VacancyDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "vacancy")
public class Vacancy {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id")
    @NotNull
    private Area area;

    @Column(name = "salary_from")
    private Integer salaryFrom;

    @Column(name = "salary_to")
    private Integer salaryTo;

    @Column(name = "salary_gross")
    private Boolean salaryGross;

    @Column(name = "salary_currency")
    private String salaryCurrency;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id")
    @NotNull
    private Employer employer;

    @Column(name = "date_create")
    @NotNull
    private LocalDateTime dateCreate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "views_count")
    @NotNull
    private Integer viewsCount;

    public Vacancy(){
    }

    public Vacancy(VacancyDTO vacancyDTO, String comment, Employer employer) {
        id = vacancyDTO.getId();
        name = vacancyDTO.getName();
        createdAt = vacancyDTO.getCreatedAt();
        salaryFrom = vacancyDTO.getSalary().getSalaryFrom();
        salaryTo = vacancyDTO.getSalary().getSalaryTo();
        salaryGross = vacancyDTO.getSalary().getSalaryGross();
        salaryCurrency = vacancyDTO.getSalary().getSalaryCurrency();
        dateCreate = LocalDateTime.now();
        this.employer = employer;
        area = new Area(vacancyDTO.getArea());
        this.comment = comment;
        viewsCount = 0;
    }

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

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Integer viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Integer getSalaryFrom() {
        return salaryFrom;
    }

    public void setSalaryFrom(Integer salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public Integer getSalaryTo() {
        return salaryTo;
    }

    public void setSalaryTo(Integer salaryTo) {
        this.salaryTo = salaryTo;
    }

    public Boolean getSalaryGross() {
        return salaryGross;
    }

    public void setSalaryGross(Boolean salaryGross) {
        this.salaryGross = salaryGross;
    }

    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }
}
