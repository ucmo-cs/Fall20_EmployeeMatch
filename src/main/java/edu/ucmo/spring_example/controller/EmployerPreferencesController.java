package edu.ucmo.spring_example.controller;


import edu.ucmo.spring_example.dao.EmployerPreferencesDao;
import edu.ucmo.spring_example.model.EmployerPreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employerpreferences")
public class EmployerPreferencesController {

    @Autowired
    private EmployerPreferencesDao employerPreferencesDao;

    @PostMapping
    public EmployerPreferences saveEmployeePreferences(@RequestBody EmployerPreferences employerPreferences){
        EmployerPreferences newEmployerPreferences = new EmployerPreferences(employerPreferences.getCompanyid(),
                employerPreferences.getEw1(),
                employerPreferences.getEw2(),
                employerPreferences.getEw3(),
                employerPreferences.getEw4(),
                employerPreferences.getEw5(),
                employerPreferences.getEo1(),
                employerPreferences.getEo2(),
                employerPreferences.getEo3(),
                employerPreferences.getEo4(),
                employerPreferences.getEo5()
        );
        return employerPreferencesDao.save(newEmployerPreferences);
    }

    @GetMapping
    public List<EmployerPreferences> listEmployerPreferences(){
        List<EmployerPreferences> list = new ArrayList<>();
        employerPreferencesDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @GetMapping("/{id}")
    public EmployerPreferences getOne(@PathVariable int id){
        Optional<EmployerPreferences> optionalEmployerPreferences = Optional.ofNullable(employerPreferencesDao.findByCompanyid(id));
        return optionalEmployerPreferences.isPresent() ? optionalEmployerPreferences.get() : null;
    }

}
