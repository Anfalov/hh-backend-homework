package ru.hh.school.Services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.DAO.VacancyDAO;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;

import java.util.List;

@Service
public class VacancyService {

    private final EmployerService employerService;
    private final VacancyDAO vacancyDAO;

    @Autowired
    public VacancyService(SessionFactory sessionFactory, EmployerService employerService) {
        vacancyDAO = new VacancyDAO(sessionFactory);
        this.employerService = employerService;
    }

    @Transactional
    public Vacancy getById(int vacancyId) {
        return vacancyDAO.get(Vacancy.class, vacancyId);
    }

    @Transactional
    public List<Vacancy> getVacancies(int page, int per_page)
    {
        List<Vacancy> vacancyList = vacancyDAO.getVacancies(page, per_page);
        for (Vacancy vacancy: vacancyList) {
            vacancy.setViewsCount(vacancy.getViewsCount() + 1);
            saveOrUpdate(vacancy);
           Employer employer = employerService.getById(vacancy.getEmployer().getId());
            if (employer != null)
            {
                employer.setViewsCount(employer.getViewsCount() + 1);
                employerService.saveOrUpdate(employer);
            }
        }
        return vacancyList;
    }

    @Transactional
    public void saveOrUpdate(Vacancy vacancy) {
        vacancyDAO.saveOrUpdate(vacancy);
    }

    @Transactional
    public void delete(Vacancy vacancy) {
        vacancyDAO.delete(vacancy);
    }

    @Transactional
    public Boolean updateComment(Integer vacancyId, String comment)
    {
        Vacancy vacancy = getById(vacancyId);
        if (vacancy == null)
            return false;
        vacancy.setComment(comment);
        saveOrUpdate(vacancy);
        return true;
    }
}
