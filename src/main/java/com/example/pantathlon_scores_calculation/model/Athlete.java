package com.example.pantathlon_scores_calculation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

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
    private LocalTime timeToAdd;
    private LocalTime finalScore;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "athlete_place", joinColumns = @JoinColumn(name = "athlete_id"), inverseJoinColumns = @JoinColumn(name = "place_id"))
    private Set<Place> places = new HashSet<>();
}