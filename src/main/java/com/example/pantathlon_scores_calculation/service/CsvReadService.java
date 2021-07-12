package com.example.pantathlon_scores_calculation.service;

import com.example.pantathlon_scores_calculation.model.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvReadService {
    @Autowired
    AthleteService athleteService;
    String filePath = "C:\\Users\\Donatas\\Downloads\\Task\\Task\\Athlete_Results.csv";

    public void readCsvFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                Athlete athlete = new Athlete();
                athlete.setFullName(values[0]);
                athlete.setFencingVictories(parseStringToInt(values[1].trim()));
                String[] swimmingTimeSplit = values[2].split("[:.]");
                athlete.setSwimmingMin(parseStringToInt(swimmingTimeSplit[0]));
                athlete.setSwimmingSec(parseStringToInt(swimmingTimeSplit[1]));
                athlete.setSwimmingMilSec(parseStringToInt(swimmingTimeSplit[2]));
                athlete.setRidingKnockDown(parseStringToInt(values[3].trim()));
                athlete.setRidingRefusal(parseStringToInt(values[4].trim()));
                athlete.setRidingDisobedienceLeading(parseStringToInt(values[5].trim()));
                athlete.setShootingScore(parseStringToInt(values[6].trim()));
                String[] runningTimeSplit = values[7].split("[:.]");
                athlete.setRunningMin(parseStringToInt(runningTimeSplit[0]));
                athlete.setRunningSec(parseStringToInt(runningTimeSplit[1]));
                athlete.setRunningMilSec(parseStringToInt(runningTimeSplit[2]));
                athleteService.addAthlete(athlete);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int parseStringToInt(String string) {
        return Integer.parseInt(string);
    }
}