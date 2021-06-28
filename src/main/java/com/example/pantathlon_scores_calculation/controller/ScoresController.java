package com.example.pantathlon_scores_calculation.controller;

import com.example.pantathlon_scores_calculation.model.Athlete;
import com.example.pantathlon_scores_calculation.service.CsvReadService;
import com.example.pantathlon_scores_calculation.service.OverallScoreCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoresController {
    @Autowired
    CsvReadService csvReadService;
    @Autowired
    OverallScoreCalculationService overallScoreCalculationService;

    String filePath = "C:\\Users\\Donatas\\Downloads\\Task\\Task\\Athlete_Results.csv";

    @GetMapping("/test")
    public List<Athlete> test() {
        csvReadService.readCsvFile(filePath);
        overallScoreCalculationService.findMaxCommonPoints();
        overallScoreCalculationService.addMinutesToConcludingEvent();
        return overallScoreCalculationService.finalScoreCount();
    }
}
