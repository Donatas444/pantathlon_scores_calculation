package com.example.pantathlon_scores_calculation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.pantathlon_scores_calculation.model.Athlete;

import java.time.LocalTime;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AthleteService.class, EventsScoresCalculationService.class,
        OverallScoreCalculationService.class})
@ExtendWith(SpringExtension.class)
public class OverallScoreCalculationServiceTest {
    @MockBean
    private AthleteService athleteService;

    @MockBean
    private EventsScoresCalculationService eventsScoresCalculationService;

    @Autowired
    private OverallScoreCalculationService overallScoreCalculationService;

    @Test
    public void testCalculateMaxCommonPoints() {
        when(this.athleteService.findMaxCommonScore()).thenReturn(3);
        when(this.athleteService.getAllAthletes()).thenReturn(new ArrayList<>());
        this.overallScoreCalculationService.calculateMaxCommonPoints();
        verify(this.athleteService).findMaxCommonScore();
        verify(this.athleteService).getAllAthletes();
        assertEquals(3, this.overallScoreCalculationService.max);
    }

    @Test
    public void testAddMinutesToConcludingEvent() {
        when(this.athleteService.getAllAthletes()).thenReturn(new ArrayList<>());
        this.overallScoreCalculationService.addMinutesToConcludingEvent();
        verify(this.athleteService).getAllAthletes();
    }

    @Test
    public void testFinalScoreCount() {
        when(this.athleteService.getAllAthletes()).thenReturn(new ArrayList<>());
        this.overallScoreCalculationService.finalScoreCount();
        verify(this.athleteService).getAllAthletes();
    }

    @Test
    public void testCalculateAthletesPlaces() {
        when(this.athleteService.getAllAthletes()).thenReturn(new ArrayList<>());
        assertTrue(this.overallScoreCalculationService.calculateAthletesPlaces().isEmpty());
        verify(this.athleteService).getAllAthletes();
    }

    @Test
    public void testCalculateAthletesPlaces2() {
        Athlete athlete = new Athlete();
        athlete.setSwimmingTime(LocalTime.of(1, 1));
        athlete.setFullName("Ponas Misteris");
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

        ArrayList<Athlete> athleteList = new ArrayList<>();
        athleteList.add(athlete);
        doNothing().when(this.athleteService).editAthlete(any());
        when(this.athleteService.getAllAthletes()).thenReturn(athleteList);
        assertEquals(1, this.overallScoreCalculationService.calculateAthletesPlaces().size());
        verify(this.athleteService).editAthlete(any());
        verify(this.athleteService).getAllAthletes();
    }

    @Test
    public void testCalculateAthletesPlaces3() {
        Athlete athlete = new Athlete();
        athlete.setSwimmingTime(LocalTime.of(1, 1));
        athlete.setFullName("Ponas Misteris");
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

        Athlete athlete1 = new Athlete();
        athlete1.setSwimmingTime(LocalTime.of(1, 1));
        athlete1.setFullName("Ponas Johnsonas");
        athlete1.setRidingKnockDown(1);
        athlete1.setCommonScore(3);
        athlete1.setFinalScore(LocalTime.of(1, 1));
        athlete1.setRunningMin(0);
        athlete1.setFencingVictories(0);
        athlete1.setRunningSec(0);
        athlete1.setShootingScore(3);
        athlete1.setSwimmingMilSec(1);
        athlete1.setRunningTime(LocalTime.of(1, 1));
        athlete1.setRunningMilSec(0);
        athlete1.setId(123L);
        athlete1.setPlace(0);
        athlete1.setSwimmingSec(1);
        athlete1.setPenaltyTime(LocalTime.of(1, 1));
        athlete1.setRidingRefusal(1);
        athlete1.setSwimmingMin(1);
        athlete1.setRidingDisobedienceLeading(1);

        ArrayList<Athlete> athleteList = new ArrayList<>();
        athleteList.add(athlete1);
        athleteList.add(athlete);
        doNothing().when(this.athleteService).editAthlete(any());
        when(this.athleteService.getAllAthletes()).thenReturn(athleteList);
        assertEquals(2, this.overallScoreCalculationService.calculateAthletesPlaces().size());
        verify(this.athleteService, times(2)).editAthlete(any());
        verify(this.athleteService).getAllAthletes();
    }
}

