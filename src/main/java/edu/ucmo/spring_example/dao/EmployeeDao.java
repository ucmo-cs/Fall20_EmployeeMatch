package edu.ucmo.spring_example.dao;

import edu.ucmo.spring_example.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    Employee findByEmail(String email);
    Employee findById(int id);
}
