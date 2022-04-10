package ru.hh.school.DTO.Employer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.hh.school.DTO.AreaDTO;
import ru.hh.school.entity.Employer;
import ru.hh.school.util.Popularity;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FavoriteEmployerDTO {

    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @JsonProperty("date_create")
    private LocalDateTime dateCreate;

    private String description;

    @NotNull
    private AreaDTO area;

    private String comment;


    private Popularity popularity;

    @JsonProperty("views_count")
    @NotNull
    private Integer viewsCount;


    public FavoriteEmployerDTO() {
    }

    public FavoriteEmployerDTO(Employer employer)
    {
        id = employer.getId();
        name = employer.getName();
        dateCreate = employer.getDateCreate();
        description = employer.getDescription();
        area = new AreaDTO(employer.getArea());
        comment = employer.getComment();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
