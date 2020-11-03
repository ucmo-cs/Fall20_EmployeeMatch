package edu.ucmo.spring_example.dao;

import edu.ucmo.spring_example.model.EmployeePreferences;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeePreferencesDao extends CrudRepository<EmployeePreferences, Integer> {

    EmployeePreferences findByUserid(int id);
}
