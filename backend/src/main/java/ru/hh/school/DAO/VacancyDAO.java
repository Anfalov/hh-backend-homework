package ru.hh.school.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Vacancy;

import java.util.List;

@Repository
public class VacancyDAO extends GenericDAO{

    @Autowired
    public VacancyDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    public List<Vacancy> getVacancies(int page, int per_page)
    {
        return getSession().createQuery("from Vacancy")
                .setFirstResult(page * per_page)
            .setMaxResults(per_page).getResultList();
    }
}
