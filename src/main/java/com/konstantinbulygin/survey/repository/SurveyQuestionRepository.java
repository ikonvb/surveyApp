package com.konstantinbulygin.survey.repository;

import com.konstantinbulygin.survey.data.SurveyQuestion;
import com.konstantinbulygin.survey.data.SurveyQuestionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyQuestionRepository extends JpaRepository<SurveyQuestion, SurveyQuestionKey> {

    @Query(value = "SELECT question_id FROM survey_question WHERE survey_id = ?1", nativeQuery = true)
    List<Integer> findAllQuestionIdForSurvey(int surveyId);
}
