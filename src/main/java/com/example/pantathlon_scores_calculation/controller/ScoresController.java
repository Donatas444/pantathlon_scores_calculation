package com.example.pantathlon_scores_calculation.controller;

import com.example.pantathlon_scores_calculation.model.Athlete;
import com.example.pantathlon_scores_calculation.service.FinalCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Used facade design pattern:
 * All needed calculation methods' implementation combined by one method "listOfFinalScores".
 * This finally is sent to @Controller.
 */

@RestController
public class ScoresController {
    @Autowired
    FinalCalculation finalCalculation;
    String filePath = "C:\\Users\\Donatas\\Downloads\\Task\\Task\\Athlete_Results.csv";

    @GetMapping("/scores")
    public List<Athlete> scores() {
        return finalCalculation.listOfFinalScores(filePath);
    }
}
