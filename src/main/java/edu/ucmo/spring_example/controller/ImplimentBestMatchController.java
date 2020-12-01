package edu.ucmo.spring_example.controller;

import edu.ucmo.spring_example.service.BestMatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("implimentAlgorithm")
public class ImplimentBestMatchController {

    @GetMapping
    public String index() throws IOException, SQLException {
        BestMatch bestMatch = new BestMatch();
        bestMatch.initializeMatch();
        return "AlgorithmImplementedView";
    }
}
