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
    SurveyRepository repository;

    public void save(Survey survey) {
        repository.save(survey);
    }

    public Optional<Survey> findById(int id) {
        return repository.findById(id);
    }

    public void delete(Survey survey) {
        repository.delete(survey);
    }

    public List<Survey> findAllSurveyByStartDate(LocalDate localDate) {
        return repository.findAllSurveyByStartDate(localDate);
    }

    public List<Survey> findAllSurvey(){
        return repository.findAllSurvey();
    }
}
