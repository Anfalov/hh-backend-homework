package ru.hh.school.DTO.Employer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.DTO.AreaDTO;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployerDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private AreaDTO area;

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
}
