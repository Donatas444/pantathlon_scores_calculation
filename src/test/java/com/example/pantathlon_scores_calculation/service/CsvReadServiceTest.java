package com.example.pantathlon_scores_calculation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.pantathlon_scores_calculation.model.Athlete;
import com.example.pantathlon_scores_calculation.model.Place;
import com.example.pantathlon_scores_calculation.repository.AthleteRepository;

import java.time.LocalTime;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CsvReadService.class})
@ExtendWith(SpringExtension.class)
public class CsvReadServiceTest {
    @MockBean
    private AthleteRepository athleteRepository;

    @Autowired
    private CsvReadService csvReadService;

    @Test
    public void testAddAthlete() {
        Athlete athlete = new Athlete();
        athlete.setFullName("Dr Jane Doe");
        athlete.setRidingKnockDown(1);
        athlete.setCommonScore(3);
        athlete.setFinalScore(LocalTime.of(1, 1));
        athlete.setRunningMin(1);
        athlete.setFencingVictories(1);
        athlete.setRunningSec(1);
        athlete.setPlaces(new HashSet<Place>());
        athlete.setShootingScore(3);
        athlete.setSwimmingMilSec(1);
        athlete.setRunningMilSec(1);
        athlete.setId(123L);
        athlete.setTimeToAdd(LocalTime.of(1, 1));
        athlete.setSwimmingSec(1);
        athlete.setRidingRefusal(1);
        athlete.setSwimmingMin(1);
        athlete.setRidingDisobedienceLeading(1);
        when(this.athleteRepository.save(any())).thenReturn(athlete);
        this.csvReadService.addAthlete();
        verify(this.athleteRepository).save(any());
    }

    @Test
    public void testReadCsvFile() {
        assertTrue(this.csvReadService.readCsvFile("/tmp/foo.txt").isEmpty());
    }

    @Test
    public void testParseStringToInt() {
        assertEquals(42, this.csvReadService.parseStringToInt("42"));
    }
}

