package ru.hh.school.util;

import javax.ws.rs.BadRequestException;

public class Validation {
    public static void paginationValidation(Integer page, Integer per_page)
    {
        if( page < 0)
            throw new BadRequestException("Page number can't be negative");
        if (per_page < 1 || per_page > 100)
            throw new BadRequestException("per_page parameter must be in the range from 1 to 100");
        if  ((page + 1) * per_page > 5000)
            throw new BadRequestException("Depth of returned results cannot be greater than 5000 records");
    }
}
