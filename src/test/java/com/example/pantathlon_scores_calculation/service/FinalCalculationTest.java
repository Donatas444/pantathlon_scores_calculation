package com.example.pantathlon_scores_calculation.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.pantathlon_scores_calculation.model.Athlete;
import com.example.pantathlon_scores_calculation.repository.AthleteRepository;

import java.time.LocalTime;

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
    private AthleteRepository athleteRepository;

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
        doNothing().when(this.csvReadService).readCsvFile();
        when(this.athleteRepository.findAll()).thenReturn(new ArrayList<>());
        List<Athlete> actualListOfFinalScoresResult = this.finalCalculation.listOfFinalScores();
        assertSame(athleteList, actualListOfFinalScoresResult);
        assertTrue(actualListOfFinalScoresResult.isEmpty());
        verify(this.overallScoreCalculationService).addMinutesToConcludingEvent();
        verify(this.overallScoreCalculationService).calculateAthletesPlaces();
        verify(this.overallScoreCalculationService).calculateMaxCommonPoints();
        verify(this.overallScoreCalculationService).finalScoreCount();
        verify(this.csvReadService).readCsvFile();
        verify(this.athleteRepository).findAll();
    }

    @Test
    public void testListOfFinalScores2() {
        ArrayList<Athlete> athleteList = new ArrayList<>();
        when(this.overallScoreCalculationService.calculateAthletesPlaces()).thenReturn(athleteList);
        doNothing().when(this.overallScoreCalculationService).finalScoreCount();
        doNothing().when(this.overallScoreCalculationService).addMinutesToConcludingEvent();
        doNothing().when(this.overallScoreCalculationService).calculateMaxCommonPoints();
        doNothing().when(this.csvReadService).readCsvFile();

        Athlete athlete = new Athlete();
        athlete.setSwimmingTime(LocalTime.of(1, 1));
        athlete.setFullName("Dr Jane Doe");
        athlete.setRidingKnockDown(1);
        athlete.setCommonScore(3);
        athlete.setFinalScore(LocalTime.of(1, 1));
        athlete.setRunningMin(0);
        athlete.setFencingVictories(0);
        athlete.setRunningSec(0);
        athlete.setShootingScore(3);
        athlete.setSwimmingMilSec(1);
        athlete.setRunningTime(LocalTime.of(1, 1));
        athlete.setRunningMilSec(0);
        athlete.setId(123L);
        athlete.setPlace(0);
        athlete.setSwimmingSec(1);
        athlete.setPenaltyTime(LocalTime.of(1, 1));
        athlete.setRidingRefusal(1);
        athlete.setSwimmingMin(1);
        athlete.setRidingDisobedienceLeading(1);

        ArrayList<Athlete> athleteList1 = new ArrayList<>();
        athleteList1.add(athlete);
        when(this.athleteRepository.findAll()).thenReturn(athleteList1);
        List<Athlete> actualListOfFinalScoresResult = this.finalCalculation.listOfFinalScores();
        assertSame(athleteList, actualListOfFinalScoresResult);
        assertTrue(actualListOfFinalScoresResult.isEmpty());
        verify(this.overallScoreCalculationService).addMinutesToConcludingEvent();
        verify(this.overallScoreCalculationService).calculateAthletesPlaces();
        verify(this.overallScoreCalculationService).calculateMaxCommonPoints();
        verify(this.overallScoreCalculationService).finalScoreCount();
        verify(this.athleteRepository).findAll();
    }
}

