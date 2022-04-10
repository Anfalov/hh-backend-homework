package ru.hh.school.resource;

import org.springframework.beans.factory.annotation.Autowired;
import ru.hh.school.DTO.CommentDTO;
import ru.hh.school.DTO.Employer.EmployerDTO;
import ru.hh.school.DTO.Employer.EmployerToFavoritesRequestDTO;
import ru.hh.school.DTO.Employer.FavoriteEmployerDTO;
import ru.hh.school.Services.AreaService;
import ru.hh.school.Services.EmployerService;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
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
@Path("/favorites/employer")
public class FavoriteEmployerResource {

    private static final Logger logger = LoggerFactory.getLogger(EmployerResource.class);
    AreaService areaService;
    EmployerService employerService;

    @Autowired
    public FavoriteEmployerResource(AreaService areaService, EmployerService employerService)
    {
        this.areaService = areaService;
        this.employerService = employerService;
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployerToFavorites(EmployerToFavoritesRequestDTO body)
    {
        if (body.getEmployerId() < 1)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, "employer_id must be positive").build();
        APIClient client = new APIClient();
        EmployerDTO employerDTO = client.getEmployerById(body.getEmployerId());
        Employer employer = new Employer(employerDTO, body.getComment());
        Area area = employer.getArea();
        areaService.saveOrUpdate(area);
        if (employerService.getById(employer.getId()) == null)
            employerService.saveOrUpdate(employer);
        return  Response.ok().build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoriteEmployers(@QueryParam("page") @DefaultValue("0") Integer page,
                                         @QueryParam("per_page") @DefaultValue("20") Integer per_page)
    {
        try {
            paginationValidation(page, per_page);
        }
        catch (BadRequestException e) {
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()).build();
        }
        List<FavoriteEmployerDTO> employers = employerService.getEmployers(page, per_page)
                .stream()
                .map(FavoriteEmployerDTO::new)
                .collect(Collectors.toList());
        return Response.ok(employers).build();
    }

    @PUT
    @Path("/{employer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editFavoriteEmployer(@PathParam("employer_id") @NotNull Integer employerId,
                                         CommentDTO comment)
    {
        if (employerId < 1)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, "Id must be positive").build();
        employerService.updateComment(employerId, comment.getComment());
        return Response.ok().build();
    }

    @DELETE
    @Path("/{employer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response DeleteEmployerFromFavorites(@PathParam("employer_id") @NotNull Integer employerId)
    {
        if (employerId < 1)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, "Id must be positive").build();
        Employer employer = employerService.getById(employerId);
        if (employer == null)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST,
                    "Employer with id " + employerId + " doesn't in favorites").build();
        employerService.delete(employer);
        return Response.ok().build();
    }

    @POST
    @Path("/{employer_id}/refresh")
    @Produces(MediaType.APPLICATION_JSON)
    public Response refreshEmployer(@PathParam("employer_id") @NotNull Integer employerId)
    {
        if (employerId < 1)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, "Id must be positive").build();
        APIClient client = new APIClient();
        EmployerDTO employerDTO = client.getEmployerById(employerId);
        Employer employer = employerService.getById(employerId);
        if (employer == null)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST,
                    "Employer with id " + employerId + " doesn't in favorites").build();
        employer.setArea(new Area(employerDTO.getArea()));
        employer.setName(employerDTO.getName());
        employer.setDescription(employerDTO.getDescription());
        employerService.saveOrUpdate(employer);
        return Response.ok().build();
    }
}
