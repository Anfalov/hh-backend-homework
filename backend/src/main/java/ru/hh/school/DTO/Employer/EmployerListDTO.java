package ru.hh.school.DTO.Employer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployerListDTO {
    private List<ShortEmployerDTO> items;

    public List<ShortEmployerDTO> getItems() {
        return items;
    }

    public void setItems(List<ShortEmployerDTO> items) {
        this.items = items;
    }
}
