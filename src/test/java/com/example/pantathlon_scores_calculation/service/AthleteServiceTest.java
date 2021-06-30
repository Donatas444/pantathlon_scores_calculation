package com.example.pantathlon_scores_calculation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
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

@ContextConfiguration(classes = {AthleteService.class})
@ExtendWith(SpringExtension.class)
public class AthleteServiceTest {
    @MockBean
    private AthleteRepository athleteRepository;

    @Autowired
    private AthleteService athleteService;

    @Test
    public void testGetAllAthletes() {
        ArrayList<Athlete> athleteList = new ArrayList<>();
        when(this.athleteRepository.findAll()).thenReturn(athleteList);
        List<Athlete> actualAllAthletes = this.athleteService.getAllAthletes();
        assertSame(athleteList, actualAllAthletes);
        assertTrue(actualAllAthletes.isEmpty());
        verify(this.athleteRepository).findAll();
        assertEquals(0, this.athleteService.findMaxCommonScore());
    }

    @Test
    public void testGetAthleteById() {
        Athlete athlete = new Athlete();
        athlete.setSwimmingTime(LocalTime.of(1, 1));
        athlete.setFullName("Ponas Misteris");
        athlete.setRidingKnockDown(1);
        athlete.setCommonScore(3);
        athlete.setFinalScore(LocalTime.of(1, 1));
        athlete.setRunningMin(1);
        athlete.setFencingVictories(1);
        athlete.setRunningSec(1);
        athlete.setShootingScore(3);
        athlete.setSwimmingMilSec(1);
        athlete.setRunningTime(LocalTime.of(1, 1));
        athlete.setRunningMilSec(1);
        athlete.setId(123L);
        athlete.setPlace(1);
        athlete.setSwimmingSec(1);
        athlete.setPenaltyTime(LocalTime.of(1, 1));
        athlete.setRidingRefusal(1);
        athlete.setSwimmingMin(1);
        athlete.setRidingDisobedienceLeading(1);
        when(this.athleteRepository.getById(any())).thenReturn(athlete);
        assertSame(athlete, this.athleteService.getAthleteById(123L));
        verify(this.athleteRepository).getById(any());
        assertEquals(0, this.athleteService.findMaxCommonScore());
    }

    @Test
    public void testEditAthlete() {
        Athlete athlete = new Athlete();
        athlete.setSwimmingTime(LocalTime.of(1, 1));
        athlete.setFullName("Ponas Misteris");
        athlete.setRidingKnockDown(1);
        athlete.setCommonScore(3);
        athlete.setFinalScore(LocalTime.of(1, 1));
        athlete.setRunningMin(1);
        athlete.setFencingVictories(1);
        athlete.setRunningSec(1);
        athlete.setShootingScore(3);
        athlete.setSwimmingMilSec(1);
        athlete.setRunningTime(LocalTime.of(1, 1));
        athlete.setRunningMilSec(1);
        athlete.setId(123L);
        athlete.setPlace(1);
        athlete.setSwimmingSec(1);
        athlete.setPenaltyTime(LocalTime.of(1, 1));
        athlete.setRidingRefusal(1);
        athlete.setSwimmingMin(1);
        athlete.setRidingDisobedienceLeading(1);
        when(this.athleteRepository.save(any())).thenReturn(athlete);
        this.athleteService.editAthlete(new Athlete());
        verify(this.athleteRepository).save(any());
        assertTrue(this.athleteService.getAllAthletes().isEmpty());
    }

    @Test
    public void testFindMaxCommonScore() {
        when(this.athleteRepository.findMaxCommonScore()).thenReturn(3);
        assertEquals(3, this.athleteService.findMaxCommonScore());
        verify(this.athleteRepository).findMaxCommonScore();
        assertTrue(this.athleteService.getAllAthletes().isEmpty());
    }
}

