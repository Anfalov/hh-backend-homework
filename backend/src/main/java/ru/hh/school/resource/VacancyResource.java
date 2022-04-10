package ru.hh.school.resource;

import ru.hh.school.DTO.Vacancy.VacancyDTO;
import ru.hh.school.DTO.Vacancy.VacancyListDTO;
import ru.hh.school.client.APIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.entity.Vacancy;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static ru.hh.school.util.Validation.paginationValidation;

@Singleton
@Path("/vacancy")
public class VacancyResource {

    private static final Logger logger = LoggerFactory.getLogger(VacancyResource.class);

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacancies(@QueryParam("query") String query,
                                 @QueryParam("page") @DefaultValue("0") Integer page,
                                 @QueryParam("per_page") @DefaultValue("20") Integer per_page) {
        try {
            paginationValidation(page, per_page);
        }
        catch (BadRequestException e) {
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, e.getMessage()).build();
        }
        APIClient client = new APIClient();
        VacancyListDTO result = client.getVacancies(query, page, per_page);
        return Response.ok(result.getItems()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getVacancy(@PathParam("id") @NotNull @Positive Integer id) {
        if (id < 1)
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, "Id must be positive").build();
        APIClient client = new APIClient();
        VacancyDTO vacancy = client.getVacancyById(id);
        return Response.ok(vacancy).build();
    }
}
