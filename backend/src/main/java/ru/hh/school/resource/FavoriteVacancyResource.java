package ru.hh.school.resource;

import org.springframework.beans.factory.annotation.Autowired;
import ru.hh.school.DTO.CommentDTO;
import ru.hh.school.DTO.Employer.EmployerToFavoritesRequestDTO;
import ru.hh.school.DTO.Vacancy.VacancyDTO;
import ru.hh.school.DTO.Vacancy.VacancyToFavoritesRequestDTO;
import ru.hh.school.DTO.Vacancy.FavoriteVacancyDTO;
import ru.hh.school.Services.AreaService;
import ru.hh.school.Services.EmployerService;
import ru.hh.school.Services.VacancyService;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.client.APIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

import static ru.hh.school.util.Validation.paginationValidation;

@Singleton
@Path("/favorites/vacancy")
public class FavoriteVacancyResource {

    private static final Logger logger = LoggerFactory.getLogger(VacancyResource.class);
    AreaService areaService;
    VacancyService vacancyService;
    EmployerService employerService;
    FavoriteEmployerResource favoriteEmployerResource;

    @Autowired
    public FavoriteVacancyResource(AreaService areaService, VacancyService vacancyService,
                                   EmployerService employerService, FavoriteEmployerResource favoriteEmployerResource)
    {
        this.areaService = areaService;
        this.vacancyService = vacancyService;
        this.employerService = employerService;
        this.favoriteEmployerResource = favoriteEmployerResource;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addVacancyToFavorites(VacancyToFavoritesRequestDTO body)
    {
        if (body.getVacancyId() < 1)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, "vacancy_id must be positive").build();
        APIClient client = new APIClient();
        VacancyDTO vacancyDTO = client.getVacancyById(body.getVacancyId());
        Employer employer = employerService.getById(vacancyDTO.getEmployer().getId());
        if (employer == null) {
            EmployerToFavoritesRequestDTO employerToFavoritesRequestDTO = new EmployerToFavoritesRequestDTO();
            employerToFavoritesRequestDTO.setEmployerId(vacancyDTO.getEmployer().getId());
            favoriteEmployerResource.addEmployerToFavorites(employerToFavoritesRequestDTO);
            employer = employerService.getById(vacancyDTO.getEmployer().getId());
        }
        Vacancy vacancy = new Vacancy(vacancyDTO, body.getComment(), employer);
        Area area = vacancy.getArea();
        areaService.saveOrUpdate(area);
        if (vacancyService.getById(vacancy.getId()) == null)
            vacancyService.saveOrUpdate(vacancy);
        return  Response.ok().build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoriteVacancies(@QueryParam("page") @DefaultValue("0") Integer page,
                                         @QueryParam("per_page") @DefaultValue("20") Integer per_page)
    {
        try {
            paginationValidation(page, per_page);
        }
        catch (BadRequestException e) {
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()).build();
        }
        List<FavoriteVacancyDTO> vacancies = vacancyService.getVacancies(page, per_page)
                .stream()
                .map(FavoriteVacancyDTO::new)
                .collect(Collectors.toList());
        return Response.ok(vacancies).build();
    }

    @PUT
    @Path("/{vacancy_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editFavoriteVacancy(@PathParam("vacancy_id") @NotNull Integer vacancyId,
                                         CommentDTO comment)
    {
        if (vacancyId < 1)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, "Id must be positive").build();
        vacancyService.updateComment(vacancyId, comment.getComment());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{vacancy_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteVacancyFromFavorites(@PathParam("vacancy_id") @NotNull Integer vacancyId)
    {
        if (vacancyId < 1)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, "Id must be positive").build();
        Vacancy vacancy = vacancyService.getById(vacancyId);
        if (vacancy == null)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST,
                    "Vacancy with id " + vacancyId + " doesn't in favorites").build();
        vacancyService.delete(vacancy);
        return Response.ok().build();
    }

    @POST
    @Path("/{vacancy_id}/refresh")
    @Produces(MediaType.APPLICATION_JSON)
    public Response refreshVacancy(@PathParam("vacancy_id") @NotNull Integer vacancyId)
    {
        if (vacancyId < 1)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, "Id must be positive").build();
        APIClient client = new APIClient();
        VacancyDTO vacancyDTO = client.getVacancyById(vacancyId);
        Vacancy vacancy = vacancyService.getById(vacancyId);
        if (vacancy == null)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST,
                    "Vacancy with id " + vacancyId + " doesn't in favorites").build();
        vacancy.setArea(new Area(vacancyDTO.getArea()));
        vacancy.setName(vacancyDTO.getName());
        vacancy.setCreatedAt(vacancyDTO.getCreatedAt());
        vacancy.setSalaryCurrency(vacancyDTO.getSalary().getSalaryCurrency());
        vacancy.setSalaryFrom(vacancyDTO.getSalary().getSalaryFrom());
        vacancy.setSalaryGross(vacancyDTO.getSalary().getSalaryGross());
        vacancy.setSalaryTo(vacancyDTO.getSalary().getSalaryTo());
        favoriteEmployerResource.refreshEmployer(vacancyDTO.getEmployer().getId());
        vacancyService.saveOrUpdate(vacancy);
        return Response.ok().build();
    }
}
