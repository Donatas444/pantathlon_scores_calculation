package com.example.pantathlon_scores_calculation.service;

import com.example.pantathlon_scores_calculation.model.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OverallScoreCalculationService {

    @Autowired
    EventsScoresCalculationService eventsScoresCalculationService;
    @Autowired
    AthleteService athleteService;

    int max = 0;

    public void calculateMaxCommonPoints() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        int result;
        for (Athlete athlete : athletes) {
            int shoot = eventsScoresCalculationService.countShootingScore(athlete.getShootingScore());
            int swim = (int) eventsScoresCalculationService.countSwimmingScore(athlete.getSwimmingMin(), athlete.getSwimmingSec(), athlete.getSwimmingMilSec());
            int ride = eventsScoresCalculationService.countRidingScore(athlete.getRidingKnockDown(), athlete.getRidingRefusal(), athlete.getRidingDisobedienceLeading());
            int fence = eventsScoresCalculationService.countFencingScore(athlete.getFencingVictories(), athletes);
            result = shoot + swim + ride + fence;
            Athlete athlete1 = athleteService.getAthleteById(athlete.getId());
            athlete1.setCommonScore(result);
            athleteService.editAthlete(athlete1);
        }
        max = athleteService.findMaxCommonScore();
    }

    public void addMinutesToConcludingEvent() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        LocalTime leaderTime;
        LocalTime timeToPlus;
        for (Athlete athlete : athletes) {
            if (athlete.getCommonScore() == max) {
                Athlete athlete1 = athleteService.getAthleteById(athlete.getId());
                leaderTime = eventsScoresCalculationService.convertIntToTime(0, 0, 0);
                athlete1.setPenaltyTime(leaderTime);
                athleteService.editAthlete(athlete1);
            } else {
                int pointsToAdd = max - athlete.getCommonScore();
                int seconds = pointsToAdd % 60;
                int totalMinutes = pointsToAdd / 60;
                int minutes = totalMinutes % 60;
                int milSeconds = 0;
                timeToPlus = eventsScoresCalculationService.convertIntToTime(minutes, seconds, milSeconds);
                Athlete athlete1 = athleteService.getAthleteById(athlete.getId());
                athlete1.setPenaltyTime(timeToPlus);
                athleteService.editAthlete(athlete1);
            }
        }
    }

    public void finalScoreCount() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        LocalTime penaltyPlusRuningTime;
        for (Athlete athlete : athletes) {
            Athlete athleteById = athleteService.getAthleteById(athlete.getId());
            LocalTime runningScore = eventsScoresCalculationService.convertIntToTime(athleteById.getRunningMin(), athleteById.getRunningSec(), athleteById.getRunningMilSec());
            penaltyPlusRuningTime = athleteById.getPenaltyTime().plusMinutes(runningScore.getMinute()).plusSeconds(runningScore.getSecond()).plusNanos(runningScore.getNano());
            athleteById.setFinalScore(penaltyPlusRuningTime);
            athleteService.editAthlete(athleteById);
        }
    }

    public List<Athlete> calculateAthletesPlaces() {
        List<Athlete> athletes = athleteService.getAllAthletes();
        AtomicInteger i = new AtomicInteger(0);
        List<Athlete> sortedAthletesByTime = athletes.stream()
                .sorted(Comparator.comparing(Athlete::getFinalScore))
                .collect(Collectors.toList());
        sortedAthletesByTime.forEach(athlete -> athlete.setPlace(i.incrementAndGet()));
        sortedAthletesByTime.forEach(athlete -> athleteService.editAthlete(athlete));
        return athletes.stream()
                .sorted(Comparator.comparing(Athlete::getPlace))
                .collect(Collectors.toList());
    }
}


