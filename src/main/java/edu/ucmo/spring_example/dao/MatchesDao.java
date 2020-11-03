package edu.ucmo.spring_example.dao;

import edu.ucmo.spring_example.model.Matches;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchesDao extends CrudRepository<Matches, Integer> {

    Matches findByUserid(int id);
    Matches findByCompanyid(int id);
}
