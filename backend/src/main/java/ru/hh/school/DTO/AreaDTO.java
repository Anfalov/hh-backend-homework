package ru.hh.school.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.entity.Area;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String name;

    public AreaDTO() {
    }

    public AreaDTO(Area area) {
        id = area.getId();
        name = area.getName();
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
}
