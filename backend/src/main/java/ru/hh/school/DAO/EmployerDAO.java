package ru.hh.school.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployerDAO extends GenericDAO{

    @Autowired
    public EmployerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    public Query getEmployers()
    {
        return getSession().createQuery("from Employer");
    }
}
