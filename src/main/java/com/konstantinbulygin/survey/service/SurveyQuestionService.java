package com.konstantinbulygin.survey.service;

import com.konstantinbulygin.survey.data.SurveyQuestion;
import com.konstantinbulygin.survey.repository.SurveyQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyQuestionService {

    @Autowired
    SurveyQuestionRepository surveyQuestionRepository;

    public void save(SurveyQuestion surveyQuestion) {
        surveyQuestionRepository.save(surveyQuestion);
    }

    public List<Integer> findAllQuestionIdForSurvey(int surveyId) {
        return surveyQuestionRepository.findAllQuestionIdForSurvey(surveyId);
    }
}
