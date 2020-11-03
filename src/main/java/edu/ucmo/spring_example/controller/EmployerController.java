package edu.ucmo.spring_example.controller;

import edu.ucmo.spring_example.dao.EmployerDao;
import edu.ucmo.spring_example.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("employer")
public class EmployerController {

    @Autowired
    private EmployerDao employerDao;

    //TODO: make post mapping work
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Employer saveEmployer(@RequestBody Employer employer){
        Employer newEmployer = new Employer(employer.getCompanyid(),
                employer.getCompanyname(),
                employer.getEmail());
        return employerDao.save(newEmployer);
    }

    @GetMapping
    public List<Employer> listEmployers(){
        List<Employer> list = new ArrayList<>();
        employerDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @GetMapping("/{id}")
    public Employer getOne(@PathVariable int id){
        Optional<Employer> optionalEmployer = Optional.ofNullable(employerDao.findByCompanyid(id));
        return optionalEmployer.isPresent() ? optionalEmployer.get() : null;
    }

}

