package ru.hh.school.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Employer;

import java.util.List;

@Repository
public class EmployerDAO extends GenericDAO{

    @Autowired
    public EmployerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    public List<Employer> getEmployers(int page, int per_page)
    {
        return getSession().createQuery("from Employer")
                .setFirstResult(page * per_page)
                .setMaxResults(per_page).getResultList();
    }
}
