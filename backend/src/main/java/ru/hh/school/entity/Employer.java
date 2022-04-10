package ru.hh.school.entity;

import ru.hh.school.DTO.Employer.EmployerDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "employer")
public class Employer {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "date_create")
    @NotNull
    private LocalDateTime dateCreate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "area_id")
    @NotNull
    private Area area;

    @Column(name = "comment")
    private String comment;

    @Column(name = "views_count")
    @NotNull
    private Integer viewsCount;

    public Employer(){
    }

    public Employer(EmployerDTO employerDTO, String comment) {
        id = employerDTO.getId();
        name = employerDTO.getName();
        dateCreate = LocalDateTime.now();
        description = employerDTO.getDescription();
        area = new Area(employerDTO.getArea());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
