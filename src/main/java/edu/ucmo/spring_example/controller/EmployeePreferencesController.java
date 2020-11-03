package edu.ucmo.spring_example.controller;

import edu.ucmo.spring_example.dao.EmployeePreferencesDao;
import edu.ucmo.spring_example.model.EmployeePreferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employeepreferences")
public class EmployeePreferencesController {

    @Autowired
    private EmployeePreferencesDao employeePreferencesDao;

    @PostMapping
    public EmployeePreferences saveEmployeePreferences(@RequestBody EmployeePreferences employeePreferences){
        EmployeePreferences newEmployeePreferences = new EmployeePreferences(employeePreferences.getUserid(),
                employeePreferences.getEw1(),
                employeePreferences.getEw2(),
                employeePreferences.getEw3(),
                employeePreferences.getEw4(),
                employeePreferences.getEw5(),
                employeePreferences.getEo1(),
                employeePreferences.getEo2(),
                employeePreferences.getEo3(),
                employeePreferences.getEo4(),
                employeePreferences.getEo5()
        );
        return employeePreferencesDao.save(newEmployeePreferences);
    }

    @GetMapping
    public List<EmployeePreferences> listEmployeePreferences(){
        List<EmployeePreferences> list = new ArrayList<>();
        employeePreferencesDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @GetMapping("/{id}")
    public EmployeePreferences getOne(@PathVariable int id){
        Optional<EmployeePreferences> optionalEmployeePreferences = Optional.ofNullable(employeePreferencesDao.findByUserid(id));
        return optionalEmployeePreferences.isPresent() ? optionalEmployeePreferences.get() : null;
    }

}
