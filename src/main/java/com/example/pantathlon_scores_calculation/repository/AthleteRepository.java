package com.example.pantathlon_scores_calculation.repository;

import com.example.pantathlon_scores_calculation.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    @Query(value = "SELECT MAX(common_score) FROM Athlete", nativeQuery = true)
    int findMaxCommonScore();
}
