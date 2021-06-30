package com.example.pantathlon_scores_calculation.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.pantathlon_scores_calculation.model.Athlete;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CsvReadService.class, FinalCalculation.class, OverallScoreCalculationService.class})
@ExtendWith(SpringExtension.class)
public class FinalCalculationTest {
    @MockBean
    private CsvReadService csvReadService;

    @Autowired
    private FinalCalculation finalCalculation;

    @MockBean
    private OverallScoreCalculationService overallScoreCalculationService;

    @Test
    public void testListOfFinalScores() {
        ArrayList<Athlete> athleteList = new ArrayList<>();
        when(this.overallScoreCalculationService.calculateAthletesPlaces()).thenReturn(athleteList);
        doNothing().when(this.overallScoreCalculationService).finalScoreCount();
        doNothing().when(this.overallScoreCalculationService).addMinutesToConcludingEvent();
        doNothing().when(this.overallScoreCalculationService).calculateMaxCommonPoints();
        when(this.csvReadService.readCsvFile(anyString())).thenReturn(new ArrayList<>());
        List<Athlete> actualListOfFinalScoresResult = this.finalCalculation.listOfFinalScores("/tmp/foo.txt");
        assertSame(athleteList, actualListOfFinalScoresResult);
        assertTrue(actualListOfFinalScoresResult.isEmpty());
        verify(this.overallScoreCalculationService).addMinutesToConcludingEvent();
        verify(this.overallScoreCalculationService).calculateAthletesPlaces();
        verify(this.overallScoreCalculationService).calculateMaxCommonPoints();
        verify(this.overallScoreCalculationService).finalScoreCount();
        verify(this.csvReadService).readCsvFile(anyString());
    }
}

