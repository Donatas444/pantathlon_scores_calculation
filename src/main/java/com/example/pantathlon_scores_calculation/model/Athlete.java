package com.example.pantathlon_scores_calculation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Athlete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private int fencingVictories;
    private int swimmingMin;
    private int swimmingSec;
    private int swimmingMilSec;
    private int ridingKnockDown;
    private int ridingRefusal;
    private int ridingDisobedienceLeading;
    private int commonScore;
    private int shootingScore;
    private int runningMin;
    private int runningSec;
    private int runningMilSec;
    private LocalTime runningTime;
    private LocalTime swimmingTime;
    private LocalTime penaltyTime;
    private LocalTime finalScore;
    private int place;
}