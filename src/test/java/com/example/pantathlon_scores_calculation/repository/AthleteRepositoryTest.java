package com.example.pantathlon_scores_calculation.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.pantathlon_scores_calculation.model.Athlete;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class AthleteRepositoryTest {
    @Autowired
    private AthleteRepository athleteRepository;

    @Test
    public void testFindMaxCommonScore() {
        Athlete athlete = new Athlete();
        athlete.setSwimmingTime(LocalTime.of(1, 1));
        athlete.setFullName("Tom Johnson");
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

        Athlete athlete1 = new Athlete();
        athlete1.setSwimmingTime(LocalTime.of(1, 1));
        athlete1.setFullName("Ponas Misteris");
        athlete1.setRidingKnockDown(1);
        athlete1.setCommonScore(3);
        athlete1.setFinalScore(LocalTime.of(1, 1));
        athlete1.setRunningMin(1);
        athlete1.setFencingVictories(1);
        athlete1.setRunningSec(1);
        athlete1.setShootingScore(3);
        athlete1.setSwimmingMilSec(1);
        athlete1.setRunningTime(LocalTime.of(1, 1));
        athlete1.setRunningMilSec(1);
        athlete1.setId(123L);
        athlete1.setPlace(1);
        athlete1.setSwimmingSec(1);
        athlete1.setPenaltyTime(LocalTime.of(1, 1));
        athlete1.setRidingRefusal(1);
        athlete1.setSwimmingMin(1);
        athlete1.setRidingDisobedienceLeading(1);
        this.athleteRepository.save(athlete);
        this.athleteRepository.save(athlete1);
        assertEquals(3, this.athleteRepository.findMaxCommonScore());
    }
}

