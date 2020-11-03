package edu.ucmo.spring_example.dao;

import edu.ucmo.spring_example.model.EmployerPreferences;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerPreferencesDao extends CrudRepository<EmployerPreferences, Integer> {

    EmployerPreferences findByCompanyid(int id);
}
