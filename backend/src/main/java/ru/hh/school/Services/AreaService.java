package ru.hh.school.Services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.DAO.GenericDAO;
import ru.hh.school.entity.Area;


@Service
public class AreaService {

    private final GenericDAO genericDAO;

    @Autowired
    public AreaService(SessionFactory sessionFactory) {
        genericDAO = new GenericDAO(sessionFactory);
    }

    @Transactional
    public Area getById(int areaId) {
        return genericDAO.get(Area.class, areaId);
    }

    @Transactional
    public void saveOrUpdate(Area area) {
        genericDAO.saveOrUpdate(area);
    }
}
