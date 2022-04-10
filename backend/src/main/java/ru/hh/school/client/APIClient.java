package ru.hh.school.client;

import ru.hh.school.DTO.Employer.EmployerDTO;
import ru.hh.school.DTO.Employer.EmployerListDTO;
import ru.hh.school.DTO.Vacancy.VacancyDTO;
import ru.hh.school.DTO.Vacancy.VacancyListDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class APIClient {
    static private final String baseURL = "https://api.hh.ru";
    private final Client client;

    public APIClient()
    {
        client = ClientBuilder.newClient();
    }

    public EmployerDTO getEmployerById(int id)
    {
        return client.target(baseURL)
                .path("/employers/" + id)
                .request(MediaType.APPLICATION_JSON)
                .header("User-Agent", "app")
                .get(EmployerDTO.class);
    }

    public EmployerListDTO getEmployers(String query, int page, int per_page)
    {
        return client.target(baseURL)
                .path("/employers")
                .queryParam("text", query)
                .queryParam("page", page)
                .queryParam("per_page", per_page)
                .request(MediaType.APPLICATION_JSON)
                .header("User-Agent", "app")
                .get(EmployerListDTO.class);
    }

    public VacancyDTO getVacancyById(int id)
    {
        return client.target(baseURL)
                .path("/vacancies/" + id)
                .request(MediaType.APPLICATION_JSON)
                .header("User-Agent", "app")
                .get(VacancyDTO.class);
    }

    public VacancyListDTO getVacancies(String query, int page, int per_page)
    {
        return client.target(baseURL)
                .path("/vacancies")
                .queryParam("text", query)
                .queryParam("page", page)
                .queryParam("per_page", per_page)
                .request(MediaType.APPLICATION_JSON)
                .header("User-Agent", "app")
                .get(VacancyListDTO.class);
    }

}
