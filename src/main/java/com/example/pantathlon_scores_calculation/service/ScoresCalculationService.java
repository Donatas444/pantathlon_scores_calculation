package com.example.pantathlon_scores_calculation.service;

import com.example.pantathlon_scores_calculation.model.Athlete;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ScoresCalculationService {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m:s");

    public int countShootingScore(int shootingScore) {
        int result;
        if (shootingScore < 172) {
            int pointsToMinus = 172 - shootingScore;
            result = 1000 - (pointsToMinus * 12);
        } else if (shootingScore > 172) {
            int pointsToAdd = shootingScore - 172;
            result = 1000 + (pointsToAdd * 12);
        } else {
            result = 1000;
        }
        return result;
    }

    public LocalTime convertIntToTime(int min, int sec) {
        String stringTime = "00:".concat(String.valueOf(min)).concat(":").concat(String.valueOf(sec));

        LocalTime time = LocalTime.parse(stringTime, formatter);
        return time;
    }

    public long countSwimmingScore(int minutes, int seconds) {
        long result;
        LocalTime swimmingTime = convertIntToTime(minutes, seconds);
        LocalTime markOf1000Points = LocalTime.parse("00:02:30", formatter);
        int value = swimmingTime.compareTo(markOf1000Points);
        if (value > 0) {
            long duration = ChronoUnit.SECONDS.between(markOf1000Points, swimmingTime);
            long swimPointsToPlus = (duration / 3) * 4;
            result = 1000 + swimPointsToPlus;
        } else if (value == 0) {
            result = 1000;
        } else {
            long duration = ChronoUnit.SECONDS.between(swimmingTime, markOf1000Points);
            long swimPointsToMinus = (duration / 3) * 4;
            result = 1000 - swimPointsToMinus;
        }
        return result;
    }

    public int countFencingScore(int victories, List<Athlete> athleteList) {
        int result;
        int percentOfVictories = (victories * 100) / (athleteList.size() - 1);
        int pointsOf70Percent = (int) Math.ceil((double) ((athleteList.size() - 1) * 70) / 100);
        if (victories == 0) {
            result = 1000 - ((athleteList.size() - 1) * 40);
        } else if (percentOfVictories < 70 && victories != 0) {
            int pointsToMinus = (pointsOf70Percent - victories) * 40;
            result = 1000 - pointsToMinus;
        } else if (percentOfVictories > 70) {
            int pointsToPlus = (victories - pointsOf70Percent) * 40;
            result = 1000 + pointsToPlus;
        } else {
            result = 1000;
        }
        return result;
    }

    public int countRidingScore(int ridingKnockDown, int ridingRefusal, int ridingDisobedienceLeading) {
        int result;
        int sumOfRidingErrors = knockDownsCounter(ridingKnockDown)
                + ridingRefusal(ridingRefusal)
                + ridingDisobedienceLeading(ridingDisobedienceLeading);
        result = 1200 - sumOfRidingErrors;
        return result;
    }

    public int knockDownsCounter(int ridingKnockDown) {
        int result = 0;
        if (ridingKnockDown != 0) {
            result = ridingKnockDown * 28;
        } else {
            return result;
        }
        return result;
    }

    public int ridingRefusal(int ridingRefusal) {
        int result = 0;
        if (ridingRefusal != 0) {
            result = ridingRefusal * 40;
        } else {
            return result;
        }
        return result;
    }

    public int ridingDisobedienceLeading(int ridingDisobedienceLeading) {
        int result = 0;
        if (ridingDisobedienceLeading != 0) {
            result = ridingDisobedienceLeading * 60;
        } else {
            return result;
        }
        return result;
    }
}
