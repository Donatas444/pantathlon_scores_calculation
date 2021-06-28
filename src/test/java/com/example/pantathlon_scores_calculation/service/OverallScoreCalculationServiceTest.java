package com.example.pantathlon_scores_calculation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.pantathlon_scores_calculation.model.Athlete;
import com.example.pantathlon_scores_calculation.model.Place;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AthleteService.class, ScoresCalculationService.class,
        OverallScoreCalculationService.class})
@ExtendWith(SpringExtension.class)
public class OverallScoreCalculationServiceTest {
    @MockBean
    private AthleteService athleteService;

    @Autowired
    private OverallScoreCalculationService overallScoreCalculationService;

    @MockBean
    private ScoresCalculationService scoresCalculationService;

    @Test
    public void testFindMaxCommonPoints() {
        when(this.athleteService.findMaxCommonScore()).thenReturn(3);
        when(this.athleteService.getAllAthletes()).thenReturn(new ArrayList<>());
        assertEquals(3, this.overallScoreCalculationService.findMaxCommonPoints());
        verify(this.athleteService).findMaxCommonScore();
        verify(this.athleteService).getAllAthletes();
        assertEquals(3, this.overallScoreCalculationService.max);
    }

    @Test
    public void testFindMaxCommonPoints2() {
        when(this.scoresCalculationService.countFencingScore(anyInt(), any())).thenReturn(3);
        when(this.scoresCalculationService.countRidingScore(anyInt(), anyInt(), anyInt())).thenReturn(3);
        when(this.scoresCalculationService.countSwimmingScore(anyInt(), anyInt())).thenReturn(3L);
        when(this.scoresCalculationService.countShootingScore(anyInt())).thenReturn(3);

        Athlete athlete = new Athlete();
        athlete.setFullName("Dr Jane Doe");
        athlete.setRidingKnockDown(1);
        athlete.setCommonScore(3);
        athlete.setFinalScore(LocalTime.of(1, 1));
        athlete.setRunningMin(0);
        athlete.setFencingVictories(0);
        athlete.setRunningSec(0);
        athlete.setPlaces(new HashSet<Place>());
        athlete.setShootingScore(3);
        athlete.setSwimmingMilSec(1);
        athlete.setRunningMilSec(0);
        athlete.setId(123L);
        athlete.setTimeToAdd(LocalTime.of(1, 1));
        athlete.setSwimmingSec(1);
        athlete.setRidingRefusal(1);
        athlete.setSwimmingMin(1);
        athlete.setRidingDisobedienceLeading(1);

        ArrayList<Athlete> athleteList = new ArrayList<>();
        athleteList.add(athlete);

        Athlete athlete1 = new Athlete();
        athlete1.setFullName("Dr Jane Doe");
        athlete1.setRidingKnockDown(1);
        athlete1.setCommonScore(3);
        athlete1.setFinalScore(LocalTime.of(1, 1));
        athlete1.setRunningMin(1);
        athlete1.setFencingVictories(1);
        athlete1.setRunningSec(1);
        athlete1.setPlaces(new HashSet<>());
        athlete1.setShootingScore(3);
        athlete1.setSwimmingMilSec(1);
        athlete1.setRunningMilSec(1);
        athlete1.setId(123L);
        athlete1.setTimeToAdd(LocalTime.of(1, 1));
        athlete1.setSwimmingSec(1);
        athlete1.setRidingRefusal(1);
        athlete1.setSwimmingMin(1);
        athlete1.setRidingDisobedienceLeading(1);
        doNothing().when(this.athleteService).editAthlete(any());
        when(this.athleteService.getAthleteById(any())).thenReturn(athlete1);
        when(this.athleteService.findMaxCommonScore()).thenReturn(3);
        when(this.athleteService.getAllAthletes()).thenReturn(athleteList);
        assertEquals(3, this.overallScoreCalculationService.findMaxCommonPoints());
        verify(this.scoresCalculationService).countFencingScore(anyInt(), any());
        verify(this.scoresCalculationService).countRidingScore(anyInt(), anyInt(), anyInt());
        verify(this.scoresCalculationService).countShootingScore(anyInt());
        verify(this.scoresCalculationService).countSwimmingScore(anyInt(), anyInt());
        verify(this.athleteService).editAthlete(any());
        verify(this.athleteService).findMaxCommonScore();
        verify(this.athleteService).getAllAthletes();
        verify(this.athleteService).getAthleteById(any());
        assertEquals(3, this.overallScoreCalculationService.max);
    }

    @Test
    public void testAddMinutesToConcludingEvent() {
        when(this.athleteService.getAllAthletes()).thenReturn(new ArrayList<>());
        this.overallScoreCalculationService.addMinutesToConcludingEvent();
        verify(this.athleteService).getAllAthletes();
    }

    @Test
    public void testAddMinutesToConcludingEvent2() {
        when(this.scoresCalculationService.convertIntToTime(anyInt(), anyInt())).thenReturn(LocalTime.of(1, 1));

        Athlete athlete = new Athlete();
        athlete.setFullName("Dr Jane Doe");
        athlete.setRidingKnockDown(1);
        athlete.setCommonScore(3);
        athlete.setFinalScore(LocalTime.of(1, 1));
        athlete.setRunningMin(0);
        athlete.setFencingVictories(0);
        athlete.setRunningSec(0);
        athlete.setPlaces(new HashSet<Place>());
        athlete.setShootingScore(3);
        athlete.setSwimmingMilSec(1);
        athlete.setRunningMilSec(0);
        athlete.setId(123L);
        athlete.setTimeToAdd(LocalTime.of(1, 1));
        athlete.setSwimmingSec(1);
        athlete.setRidingRefusal(1);
        athlete.setSwimmingMin(1);
        athlete.setRidingDisobedienceLeading(1);

        ArrayList<Athlete> athleteList = new ArrayList<Athlete>();
        athleteList.add(athlete);

        Athlete athlete1 = new Athlete();
        athlete1.setFullName("Dr Jane Doe");
        athlete1.setRidingKnockDown(1);
        athlete1.setCommonScore(3);
        athlete1.setFinalScore(LocalTime.of(1, 1));
        athlete1.setRunningMin(1);
        athlete1.setFencingVictories(1);
        athlete1.setRunningSec(1);
        athlete1.setPlaces(new HashSet<Place>());
        athlete1.setShootingScore(3);
        athlete1.setSwimmingMilSec(1);
        athlete1.setRunningMilSec(1);
        athlete1.setId(123L);
        athlete1.setTimeToAdd(LocalTime.of(1, 1));
        athlete1.setSwimmingSec(1);
        athlete1.setRidingRefusal(1);
        athlete1.setSwimmingMin(1);
        athlete1.setRidingDisobedienceLeading(1);
        doNothing().when(this.athleteService).editAthlete(any());
        when(this.athleteService.getAthleteById(any())).thenReturn(athlete1);
        when(this.athleteService.getAllAthletes()).thenReturn(athleteList);
        this.overallScoreCalculationService.addMinutesToConcludingEvent();
        verify(this.scoresCalculationService).convertIntToTime(anyInt(), anyInt());
        verify(this.athleteService).editAthlete(any());
        verify(this.athleteService).getAllAthletes();
        verify(this.athleteService).getAthleteById(any());
    }

    @Test
    public void testFinalScoreCount() {
        ArrayList<Athlete> athleteList = new ArrayList<>();
        when(this.athleteService.getAllAthletes()).thenReturn(athleteList);
        List<Athlete> actualFinalScoreCountResult = this.overallScoreCalculationService.finalScoreCount();
        assertSame(athleteList, actualFinalScoreCountResult);
        assertTrue(actualFinalScoreCountResult.isEmpty());
        verify(this.athleteService).getAllAthletes();
    }

    @Test
    public void testFinalScoreCount2() {
        when(this.scoresCalculationService.convertIntToTime(anyInt(), anyInt())).thenReturn(LocalTime.of(1, 1));

        Athlete athlete = new Athlete();
        athlete.setFullName("Dr Jane Doe");
        athlete.setRidingKnockDown(1);
        athlete.setCommonScore(3);
        athlete.setFinalScore(LocalTime.of(1, 1));
        athlete.setRunningMin(0);
        athlete.setFencingVictories(0);
        athlete.setRunningSec(0);
        athlete.setPlaces(new HashSet<>());
        athlete.setShootingScore(3);
        athlete.setSwimmingMilSec(1);
        athlete.setRunningMilSec(0);
        athlete.setId(123L);
        athlete.setTimeToAdd(LocalTime.of(1, 1));
        athlete.setSwimmingSec(1);
        athlete.setRidingRefusal(1);
        athlete.setSwimmingMin(1);
        athlete.setRidingDisobedienceLeading(1);

        ArrayList<Athlete> athleteList = new ArrayList<>();
        athleteList.add(athlete);

        Athlete athlete1 = new Athlete();
        athlete1.setFullName("Dr Jane Doe");
        athlete1.setRidingKnockDown(1);
        athlete1.setCommonScore(3);
        athlete1.setFinalScore(LocalTime.of(1, 1));
        athlete1.setRunningMin(1);
        athlete1.setFencingVictories(1);
        athlete1.setRunningSec(1);
        athlete1.setPlaces(new HashSet<>());
        athlete1.setShootingScore(3);
        athlete1.setSwimmingMilSec(1);
        athlete1.setRunningMilSec(1);
        athlete1.setId(123L);
        athlete1.setTimeToAdd(LocalTime.of(1, 1));
        athlete1.setSwimmingSec(1);
        athlete1.setRidingRefusal(1);
        athlete1.setSwimmingMin(1);
        athlete1.setRidingDisobedienceLeading(1);
        doNothing().when(this.athleteService).editAthlete(any());
        when(this.athleteService.getAthleteById(any())).thenReturn(athlete1);
        when(this.athleteService.getAllAthletes()).thenReturn(athleteList);
        List<Athlete> actualFinalScoreCountResult = this.overallScoreCalculationService.finalScoreCount();
        assertSame(athleteList, actualFinalScoreCountResult);
        assertEquals(1, actualFinalScoreCountResult.size());
        verify(this.scoresCalculationService).convertIntToTime(anyInt(), anyInt());
        verify(this.athleteService).editAthlete(any());
        verify(this.athleteService).getAllAthletes();
        verify(this.athleteService).getAthleteById(any());
    }
}

