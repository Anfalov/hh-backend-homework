package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.DAO.EmployerDAO;
import ru.hh.school.DAO.GenericDAO;
import ru.hh.school.DAO.VacancyDAO;
import ru.hh.school.DTO.AreaDTO;
import ru.hh.school.DTO.CommentDTO;
import ru.hh.school.DTO.Employer.*;
import ru.hh.school.DTO.SalaryDTO;
import ru.hh.school.DTO.Vacancy.FavoriteVacancyDTO;
import ru.hh.school.DTO.Vacancy.VacancyDTO;
import ru.hh.school.DTO.Vacancy.VacancyListDTO;
import ru.hh.school.DTO.Vacancy.VacancyToFavoritesRequestDTO;
import ru.hh.school.Services.AreaService;
import ru.hh.school.Services.EmployerService;
import ru.hh.school.Services.VacancyService;
import ru.hh.school.client.APIClient;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.resource.*;

@Configuration
@Import({
        ExampleResource.class,
        EmployerResource.class,
        FavoriteEmployerResource.class,
        VacancyResource.class,
        FavoriteVacancyResource.class,
        NabCommonConfig.class,
        APIClient.class,
        EmployerDAO.class,
        GenericDAO.class,
        VacancyDAO.class,
        AreaService.class,
        EmployerService.class,
        VacancyService.class,
        Area.class,
        Employer.class,
        Vacancy.class
})
public class CommonConfig {

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.school.entity");
    return mappingConfig;
  }
}
