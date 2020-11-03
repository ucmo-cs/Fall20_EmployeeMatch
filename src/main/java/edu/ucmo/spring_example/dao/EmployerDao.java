package edu.ucmo.spring_example.dao;

import edu.ucmo.spring_example.model.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerDao extends CrudRepository<Employer, Integer> {

    Employer findByCompanyid(int companyId);
}
