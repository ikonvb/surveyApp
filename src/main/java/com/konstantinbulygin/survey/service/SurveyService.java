package com.konstantinbulygin.survey.service;

import com.konstantinbulygin.survey.model.Survey;
import com.konstantinbulygin.survey.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    public void save(Survey survey) {
        surveyRepository.save(survey);
    }

    public Optional<Survey> findById(int id) {
        return surveyRepository.findById(id);
    }

    public void delete(Survey survey) {
        surveyRepository.delete(survey);
    }

    public List<Survey> findAllSurveyByStartDate(LocalDate localDate) {
        return surveyRepository.findAllSurveyByStartDate(localDate);
    }

    public List<Survey> findAllSurvey() {
        return surveyRepository.findAllSurvey();
    }
}
