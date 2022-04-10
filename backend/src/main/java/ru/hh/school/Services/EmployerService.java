package ru.hh.school.Services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.DAO.EmployerDAO;
import ru.hh.school.entity.Employer;

import java.util.List;

@Service
public class EmployerService {


    private final EmployerDAO employerDAO;
    @Autowired
    public EmployerService(SessionFactory sessionFactory) {
        employerDAO = new EmployerDAO(sessionFactory);
    }

    @Transactional
    public Employer getById(int employerId) {
        return employerDAO.get(Employer.class, employerId);
    }

    @Transactional
    public List<Employer> getEmployers(int page, int per_page)
    {
        List<Employer> employerList = employerDAO.getEmployers()
                .setFirstResult(page * per_page)
                .setMaxResults(per_page).getResultList();
        for (Employer employer: employerList) {
            employer.setViewsCount(employer.getViewsCount() + 1);
            saveOrUpdate(employer);
        }
        System.out.println("imthere " + employerList.size());
        return employerList;
    }

    @Transactional
    public void saveOrUpdate(Employer employer) {
        employerDAO.saveOrUpdate(employer);
    }

    @Transactional
    public void delete(Employer employer) {
        employerDAO.delete(employer);
    }

    @Transactional
    public Boolean updateComment(Integer employerId, String comment)
    {
        Employer employer = getById(employerId);
        if (employer == null)
            return false;
        employer.setComment(comment);
        saveOrUpdate(employer);
        return true;
    }
}
