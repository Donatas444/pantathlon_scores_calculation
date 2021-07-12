package com.example.pantathlon_scores_calculation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AthleteService.class, CsvReadService.class})
@ExtendWith(SpringExtension.class)
public class CsvReadServiceTest {
    @MockBean
    private AthleteService athleteService;

    @Autowired
    private CsvReadService csvReadService;

    @Test
    public void testReadCsvFile() {
        doNothing().when(this.athleteService).addAthlete(any());
        this.csvReadService.readCsvFile();
        verify(this.athleteService, times(10)).addAthlete(any());
    }

    @Test
    public void testParseStringToInt() {
        assertEquals(42, this.csvReadService.parseStringToInt("42"));
    }
}

