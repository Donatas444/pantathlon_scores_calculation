package com.example.pantathlon_scores_calculation.service;

import com.example.pantathlon_scores_calculation.model.Athlete;
import com.example.pantathlon_scores_calculation.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalCalculation {
    @Autowired
    CsvReadService csvReadService;
    @Autowired
    OverallScoreCalculationService overallScoreCalculationService;
    @Autowired
    AthleteRepository athleteRepository;

    public List<Athlete> listOfFinalScores() {
        if (athleteRepository.findAll().isEmpty()) {
            csvReadService.readCsvFile();
        }
        overallScoreCalculationService.calculateMaxCommonPoints();
        overallScoreCalculationService.addMinutesToConcludingEvent();
        overallScoreCalculationService.finalScoreCount();
        return overallScoreCalculationService.calculateAthletesPlaces();
    }
}
