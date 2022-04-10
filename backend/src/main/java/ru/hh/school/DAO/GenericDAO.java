package ru.hh.school.DAO;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenericDAO {
    private final SessionFactory sessionFactory;
    @Autowired
    public GenericDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> T get(Class<T> clazz, Integer id) {
        return getSession().get(clazz, id);
    }

    public void saveOrUpdate(Object object) {
        if (object == null) {
            return;
        }
        getSession().saveOrUpdate(object);
    }

    public void delete(Object object){
        if (object == null)
            return;
        getSession().delete(object);
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
