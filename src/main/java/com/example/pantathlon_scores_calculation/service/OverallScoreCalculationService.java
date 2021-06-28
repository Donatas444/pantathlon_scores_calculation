package com.example.pantathlon_scores_calculation.service;

import com.example.pantathlon_scores_calculation.model.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

@Service
public class OverallScoreCalculationService {

    @Autowired
    ScoresCalculationService scoresCalculationService;
    @Autowired
    AthleteService athleteService;

    int max = 0;

    public int findMaxCommonPoints() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        int result = 0;
        for (Athlete athlete : athletes) {
            int shoot = scoresCalculationService.countShootingScore(athlete.getShootingScore());
            int swim = (int) scoresCalculationService.countSwimmingScore(athlete.getSwimmingMin(), athlete.getSwimmingSec());
            int ride = scoresCalculationService.countRidingScore(athlete.getRidingKnockDown(), athlete.getRidingRefusal(), athlete.getRidingDisobedienceLeading());
            int fence = scoresCalculationService.countFencingScore(athlete.getFencingVictories(), athletes);
            result = shoot + swim + ride + fence;
            Athlete athlete1 = athleteService.getAthleteById(athlete.getId());
            athlete1.setCommonScore(result);
            athleteService.editAthlete(athlete1);
        }
        max = athleteService.findMaxCommonScore();

        return max;
    }

    public void addMinutesToConcludingEvent() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        LocalTime leaderTime;
        LocalTime timeToPlus = null;
        for (Athlete athlete : athletes) {
            if (athlete.getCommonScore() == max) {
                Athlete athlete1 = athleteService.getAthleteById(athlete.getId());
                leaderTime = scoresCalculationService.convertIntToTime(0, 0);
                athlete1.setTimeToAdd(leaderTime);
                athleteService.editAthlete(athlete1);
            } else {
                int pointsToAdd = max - athlete.getCommonScore();
                int seconds = pointsToAdd % 60;
                int totalMinutes = pointsToAdd / 60;
                int minutes = totalMinutes % 60;
                timeToPlus = scoresCalculationService.convertIntToTime(minutes, seconds);
                Athlete athlete1 = athleteService.getAthleteById(athlete.getId());
                athlete1.setTimeToAdd(timeToPlus);
                athleteService.editAthlete(athlete1);
            }
        }
    }

    public List<Athlete> finalScoreCount() {
        LocalTime time = null;
        List<Athlete> athletes = athleteService.getAllAthletes();
        for (Athlete athlete : athletes) {
            Athlete athlete1 = athleteService.getAthleteById(athlete.getId());
            LocalTime runningScore = scoresCalculationService.convertIntToTime(athlete1.getRunningMin(), athlete1.getRunningSec());
            time = athlete1.getTimeToAdd().plusSeconds(runningScore.getSecond());
            athlete1.setFinalScore(time);
            athleteService.editAthlete(athlete1);
        }
        return athletes;
    }
}


