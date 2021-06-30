package com.example.pantathlon_scores_calculation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.pantathlon_scores_calculation.model.Athlete;

import java.time.LocalTime;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EventsScoresCalculationService.class})
@ExtendWith(SpringExtension.class)
public class EventsScoresCalculationServiceTest {
    @Autowired
    private EventsScoresCalculationService eventsScoresCalculationService;

    @Test
    public void testCountShootingScore() {
        assertEquals(-1028, this.eventsScoresCalculationService.countShootingScore(3));
        assertEquals(1000, this.eventsScoresCalculationService.countShootingScore(172));
        assertEquals(10936, this.eventsScoresCalculationService.countShootingScore(1000));
    }

    @Test
    public void testCountSwimmingScore() {
        assertEquals(888L, this.eventsScoresCalculationService.countSwimmingScore(1, 3, 3));
        assertEquals(1044L, this.eventsScoresCalculationService.countSwimmingScore(3, 3, 3));
    }

    @Test
    public void testCountFencingScore() {
        assertEquals(1120, this.eventsScoresCalculationService.countFencingScore(3, new ArrayList<>()));
        assertEquals(1040, this.eventsScoresCalculationService.countFencingScore(0, new ArrayList<>()));
        assertEquals(-11000, this.eventsScoresCalculationService.countFencingScore(-300, new ArrayList<>()));
    }

    @Test
    public void testCountFencingScore2() {
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
        assertThrows(ArithmeticException.class,
                () -> this.eventsScoresCalculationService.countFencingScore(3, athleteList));
    }

    @Test
    public void testCountRidingScore() {
        assertEquals(1072, this.eventsScoresCalculationService.countRidingScore(1, 1, 1));
        assertEquals(1100, this.eventsScoresCalculationService.countRidingScore(0, 1, 1));
        assertEquals(1112, this.eventsScoresCalculationService.countRidingScore(1, 0, 1));
        assertEquals(1132, this.eventsScoresCalculationService.countRidingScore(1, 1, 0));
    }

    @Test
    public void testRidingKnockDownsCounter() {
        assertEquals(28, this.eventsScoresCalculationService.ridingKnockDownsCounter(1));
        assertEquals(0, this.eventsScoresCalculationService.ridingKnockDownsCounter(0));
    }

    @Test
    public void testRidingRefusalCounter() {
        assertEquals(40, this.eventsScoresCalculationService.ridingRefusalCounter(1));
        assertEquals(0, this.eventsScoresCalculationService.ridingRefusalCounter(0));
    }

    @Test
    public void testRidingDisobedienceLeadingCounter() {
        assertEquals(60, this.eventsScoresCalculationService.ridingDisobedienceLeadingCounter(1));
        assertEquals(0, this.eventsScoresCalculationService.ridingDisobedienceLeadingCounter(0));
    }
}

