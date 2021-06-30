package com.example.pantathlon_scores_calculation.controller;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.example.pantathlon_scores_calculation.model.Athlete;
import com.example.pantathlon_scores_calculation.service.FinalCalculation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ScoresController.class})
@ExtendWith(SpringExtension.class)
public class ScoresControllerTest {
    @MockBean
    private FinalCalculation finalCalculation;

    @Autowired
    private ScoresController scoresController;

    @Test
    public void testScores() throws Exception {
        when(this.finalCalculation.listOfFinalScores(anyString())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/scores");
        MockMvcBuilders.standaloneSetup(this.scoresController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

