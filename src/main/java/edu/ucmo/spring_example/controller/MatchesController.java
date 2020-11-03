package edu.ucmo.spring_example.controller;

import edu.ucmo.spring_example.dao.MatchesDao;
import edu.ucmo.spring_example.model.Matches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("matches")
public class MatchesController {

    @Autowired
    private MatchesDao matchesDao;

    //TODO: make post mapping work
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Matches saveEmployer(@RequestBody Matches matches){
        Matches newMatches = new Matches(matches.getUserid(),
                matches.getCompanyid());
        return matchesDao.save(newMatches);
    }

    @GetMapping
    public List<Matches> listEmployers(){
        List<Matches> list = new ArrayList<>();
        matchesDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @GetMapping("employeeid/{id}")
    public Matches getOneByEmployee(@PathVariable int id){
        Optional<Matches> optionalMatches = Optional.ofNullable(matchesDao.findByUserid(id));
        return optionalMatches.isPresent() ? optionalMatches.get() : null;
    }
    @GetMapping("employerid/{id}")
    public Matches getOneByEmployer(@PathVariable int id){
        Optional<Matches> optionalMatches = Optional.ofNullable(matchesDao.findByCompanyid(id));
        return optionalMatches.isPresent() ? optionalMatches.get() : null;
    }

}

