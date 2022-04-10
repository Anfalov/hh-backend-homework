package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.DAO.EmployerDAO;
import ru.hh.school.DAO.GenericDAO;
import ru.hh.school.Services.AreaService;
import ru.hh.school.Services.EmployerService;
import ru.hh.school.client.APIClient;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.resource.ExampleResource;
import ru.hh.school.resource.FavoriteEmployerResource;

@Configuration
@Import({
  // import your beans here
  ExampleResource.class,
  EmployerResource.class,
  FavoriteEmployerResource.class,
  NabCommonConfig.class,
  APIClient.class,
        EmployerDAO.class,
        GenericDAO.class,
        AreaService.class,
        EmployerService.class,
        Area.class,
        Employer.class
})
public class CommonConfig {

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.school.entity");
    return mappingConfig;
  }
}
