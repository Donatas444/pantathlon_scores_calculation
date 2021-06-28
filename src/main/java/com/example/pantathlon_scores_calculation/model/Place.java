package com.example.pantathlon_scores_calculation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int place;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "places")
    private Set<Athlete> athletes = new HashSet<>();
}
