package com.example.pantathlon_scores_calculation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.pantathlon_scores_calculation.model.Athlete;
import com.example.pantathlon_scores_calculation.model.Place;

import java.time.LocalTime;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ScoresCalculationService.class})
@ExtendWith(SpringExtension.class)
public class ScoresCalculationServiceTest {
    @Autowired
    private ScoresCalculationService scoresCalculationService;

    @Test
    public void testCountShootingScore() {
        assertEquals(-1028, this.scoresCalculationService.countShootingScore(3));
        assertEquals(1000, this.scoresCalculationService.countShootingScore(172));
        assertEquals(10936, this.scoresCalculationService.countShootingScore(1000));
    }

    @Test
    public void testCountSwimmingScore() {
        assertEquals(884L, this.scoresCalculationService.countSwimmingScore(1, 3));
        assertEquals(1044L, this.scoresCalculationService.countSwimmingScore(3, 3));
    }

    @Test
    public void testCountFencingScore() {
        assertEquals(1120, this.scoresCalculationService.countFencingScore(3, new ArrayList<>()));
        assertEquals(1040, this.scoresCalculationService.countFencingScore(0, new ArrayList<>()));
        assertEquals(-11000, this.scoresCalculationService.countFencingScore(-300, new ArrayList<>()));
    }

    @Test
    public void testCountFencingScore2() {
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
        assertThrows(ArithmeticException.class, () -> this.scoresCalculationService.countFencingScore(3, athleteList));
    }

    @Test
    public void testCountRidingScore() {
        assertEquals(1072, this.scoresCalculationService.countRidingScore(1, 1, 1));
        assertEquals(1100, this.scoresCalculationService.countRidingScore(0, 1, 1));
        assertEquals(1112, this.scoresCalculationService.countRidingScore(1, 0, 1));
        assertEquals(1132, this.scoresCalculationService.countRidingScore(1, 1, 0));
    }

    @Test
    public void testKnockDownsCounter() {
        assertEquals(28, this.scoresCalculationService.knockDownsCounter(1));
        assertEquals(0, this.scoresCalculationService.knockDownsCounter(0));
    }

    @Test
    public void testRidingRefusal() {
        assertEquals(40, this.scoresCalculationService.ridingRefusal(1));
        assertEquals(0, this.scoresCalculationService.ridingRefusal(0));
    }

    @Test
    public void testRidingDisobedienceLeading() {
        assertEquals(60, this.scoresCalculationService.ridingDisobedienceLeading(1));
        assertEquals(0, this.scoresCalculationService.ridingDisobedienceLeading(0));
    }
}

