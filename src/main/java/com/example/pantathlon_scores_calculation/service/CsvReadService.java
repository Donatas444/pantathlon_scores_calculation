package com.example.pantathlon_scores_calculation.service;

import com.example.pantathlon_scores_calculation.model.Athlete;
import com.example.pantathlon_scores_calculation.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReadService {

    public String fullName;
    public int fencingVictories;
    public int swimmingMin;
    public int swimmingSec;
    public int swimmingMilSec;
    public int ridingKnockDown;
    public int ridingRefusal;
    public int ridingDisobedienceLeading;
    public int shootingScore;
    public int runningMin;
    public int runningSec;
    public int runningMilSec;
    @Autowired
    AthleteRepository athleteRepository;
    List<Athlete> athleteList = new ArrayList<>();

    public void addAthlete() {
        Athlete athlete = new Athlete();
        athlete.setFullName(fullName);
        athlete.setFencingVictories(fencingVictories);
        athlete.setSwimmingMin(swimmingMin);
        athlete.setSwimmingSec(swimmingSec);
        athlete.setSwimmingMilSec(swimmingMilSec);
        athlete.setRidingKnockDown(ridingKnockDown);
        athlete.setRidingRefusal(ridingRefusal);
        athlete.setRidingDisobedienceLeading(ridingDisobedienceLeading);
        athlete.setCommonScore(0);
        athlete.setShootingScore(shootingScore);
        athlete.setRunningMin(runningMin);
        athlete.setRunningSec(runningSec);
        athlete.setRunningMilSec(runningMilSec);
        athleteRepository.save(athlete);
    }

    public List<Athlete> readCsvFile(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                fullName = values[0];
               fencingVictories = parseStringToInt(values[1].trim());
                String[] swimmingTimeSplit = values[2].split("[:.]");
                swimmingMin = Integer.parseInt(swimmingTimeSplit[0]);
                swimmingSec = Integer.parseInt(swimmingTimeSplit[1]);
                swimmingMilSec = Integer.parseInt(swimmingTimeSplit[2]);
                ridingKnockDown = parseStringToInt(values[3].trim());
                ridingRefusal = parseStringToInt(values[4].trim());
                ridingDisobedienceLeading = parseStringToInt(values[5].trim());
                shootingScore = parseStringToInt(values[6].trim());
                String[] runningTimeSplit = values[7].split("[:.]");
                runningMin = Integer.parseInt(runningTimeSplit[0]);
                runningSec = Integer.parseInt(runningTimeSplit[1]);
                runningMilSec = Integer.parseInt(runningTimeSplit[2]);
                addAthlete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return athleteList;
    }
    public int parseStringToInt(String string) {
        return Integer.parseInt(string);
    }
}