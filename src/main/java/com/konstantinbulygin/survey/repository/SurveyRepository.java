package com.konstantinbulygin.survey.repository;

import com.konstantinbulygin.survey.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

//    List<Survey> findAll();

    @Query(value = "SELECT * FROM surveys s WHERE s.start_date <= ?1", nativeQuery = true)
    List<Survey> findAllSurveyByStartDate(LocalDate date);

    @Query(value = "SELECT * FROM surveys", nativeQuery = true)
    List<Survey> findAllSurvey();
}
