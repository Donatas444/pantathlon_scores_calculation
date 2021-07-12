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
    @Autowired
    EventsScoresCalculationService eventsScoresCalculationService;

    public void addAthlete(Athlete athlete) {
        Athlete athlete1 = new Athlete();
        athlete1.setFullName(athlete.getFullName());
        athlete1.setFencingVictories(athlete.getFencingVictories());
        athlete1.setSwimmingMin(athlete.getSwimmingMin());
        athlete1.setSwimmingSec(athlete.getSwimmingSec());
        athlete1.setSwimmingMilSec(athlete.getSwimmingMilSec());
        athlete1.setSwimmingTime(eventsScoresCalculationService.convertIntToTime(
                athlete.getSwimmingMin(),
                athlete.getSwimmingSec(),
                athlete.getSwimmingMilSec()));
        athlete1.setRidingKnockDown(athlete.getRidingKnockDown());
        athlete1.setRidingRefusal(athlete.getRidingRefusal());
        athlete1.setRidingDisobedienceLeading(athlete.getRidingDisobedienceLeading());
        athlete1.setCommonScore(0);
        athlete1.setShootingScore(athlete.getShootingScore());
        athlete1.setRunningMin(athlete.getRunningMin());
        athlete1.setRunningSec(athlete.getRunningSec());
        athlete1.setRunningMilSec(athlete.getRunningMilSec());
        athlete1.setRunningTime(eventsScoresCalculationService.convertIntToTime(
                athlete.getRunningMin(),
                athlete.getRunningSec(),
                athlete.getRunningMilSec()));
        athleteRepository.save(athlete1);
    }

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
