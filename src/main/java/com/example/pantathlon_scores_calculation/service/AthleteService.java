package com.example.pantathlon_scores_calculation.service;

import com.example.pantathlon_scores_calculation.model.Athlete;
import com.example.pantathlon_scores_calculation.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {

    @Autowired
    AthleteRepository athleteRepository;

    public List<Athlete> getAllAthletes() {
        return athleteRepository.findAll();
    }

    public Athlete getAthleteById(Long id) {
        return athleteRepository.getById(id);
    }

    public void editAthlete(Athlete athlete) {
        athleteRepository.save(athlete);
    }

    public int findMaxCommonScore() {
        return athleteRepository.findMaxCommonScore();
    }
}
