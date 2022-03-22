package com.konstantinbulygin.survey.repository;

import com.konstantinbulygin.survey.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAllByClientId(Integer clientId);

    List<Answer> findAllBySurveyId(Integer id);

    @Query(value = "SELECT survey_id FROM answers WHERE survey_id = ?1", nativeQuery = true)
    List<Integer> findSurveysIdByClientId(int clientId);

    List<Integer> findAllSurveyIdByClientId(int clientId);
}
