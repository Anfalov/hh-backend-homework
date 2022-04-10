package ru.hh.school.DTO.Vacancy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.hh.school.DTO.Employer.ShortEmployerDTO;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyListDTO {
    private List<ShortEmployerDTO> items;

    public List<ShortEmployerDTO> getItems() {
        return items;
    }

    public void setItems(List<ShortEmployerDTO> items) {
        this.items = items;
    }
}
