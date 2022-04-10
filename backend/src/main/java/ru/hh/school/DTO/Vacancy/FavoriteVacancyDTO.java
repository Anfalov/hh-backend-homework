package ru.hh.school.DTO.Vacancy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.hh.school.DTO.AreaDTO;
import ru.hh.school.DTO.SalaryDTO;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.util.Popularity;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FavoriteVacancyDTO {

    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @JsonProperty("date_create")
    private LocalDateTime dateCreate;

    @NotNull
    private AreaDTO area;

    private SalaryDTO salary;

    @NotNull
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @NotNull
    private Employer employer;

    private String comment;

    private Popularity popularity;

    @JsonProperty("views_count")
    @NotNull
    private Integer viewsCount;


    public FavoriteVacancyDTO() {
    }

    public FavoriteVacancyDTO(Vacancy vacancy)
    {
        id = vacancy.getId();
        name = vacancy.getName();
        dateCreate = vacancy.getDateCreate();
        area = new AreaDTO(vacancy.getArea());
        salary = new SalaryDTO();
        salary.setSalaryTo(vacancy.getSalaryTo());
        salary.setSalaryFrom(vacancy.getSalaryFrom());
        salary.setSalaryGross(vacancy.getSalaryGross());
        salary.setSalaryCurrency(vacancy.getSalaryCurrency());
        createdAt = vacancy.getCreatedAt();
        employer = vacancy.getEmployer();
        comment = vacancy.getComment();
        if (employer.getViewsCount() > 50)
            popularity = Popularity.POPULAR;
        else
            popularity = Popularity.REGULAR;
        viewsCount = employer.getViewsCount();
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

    public AreaDTO getArea() {
        return area;
    }

    public void setArea(AreaDTO area) {
        this.area = area;
    }

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(LocalDateTime dateCreate) {
        this.dateCreate = dateCreate;
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

    public Popularity getPopularity() {
        return popularity;
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }

    public SalaryDTO getSalary() {
        return salary;
    }

    public void setSalary(SalaryDTO salary) {
        this.salary = salary;
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
