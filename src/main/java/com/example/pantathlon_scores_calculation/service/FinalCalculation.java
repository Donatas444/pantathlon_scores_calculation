package com.example.pantathlon_scores_calculation.service;

import com.example.pantathlon_scores_calculation.model.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalCalculation {
    @Autowired
    CsvReadService csvReadService;
    @Autowired
    OverallScoreCalculationService overallScoreCalculationService;

    public List<Athlete> listOfFinalScores(String filePath) {
        csvReadService.readCsvFile(filePath);
        overallScoreCalculationService.calculateMaxCommonPoints();
        overallScoreCalculationService.addMinutesToConcludingEvent();
        overallScoreCalculationService.finalScoreCount();
        return overallScoreCalculationService.calculateAthletesPlaces();
    }
}
