package edu.ucmo.spring_example.dao;

import edu.ucmo.spring_example.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarDao extends CrudRepository<Car, Integer> {

    List<Car> findByMake(String make);
}
