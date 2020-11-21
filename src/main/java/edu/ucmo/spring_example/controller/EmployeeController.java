package edu.ucmo.spring_example.controller;

import edu.ucmo.spring_example.dao.EmployeeDao;
import edu.ucmo.spring_example.dao.EmployerDao;
import edu.ucmo.spring_example.model.Employee;
import edu.ucmo.spring_example.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    //TODO: make post mapping work
    //(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        Employee newEmployee = new Employee(
                employee.getFirstn(),
                employee.getLastn(),
                employee.getEmail(),
                employee.getPassHash());
        return employeeDao.save(newEmployee);
    }


}

