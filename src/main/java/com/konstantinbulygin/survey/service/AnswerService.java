package com.konstantinbulygin.survey.service;

import com.konstantinbulygin.survey.model.Answer;
import com.konstantinbulygin.survey.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    public void saveAll(List<Answer> answers) {
        answerRepository.saveAll(answers);
    }

    public List<Answer> findAllByClientId(int clientId) {
        return answerRepository.findAllByClientId(clientId);
    }

    public List<Integer> findSurveysIdByClientId(int clientId) {
        return answerRepository.findSurveysIdByClientId(clientId);
    }

    public List<Answer> findAllBySurveyId(Integer id) {
        return answerRepository.findAllBySurveyId(id);
    }

    public List<Integer> findAllSurveyIdByClientId(int clientId) {
        return answerRepository.findAllSurveyIdByClientId(clientId);
    }
}
