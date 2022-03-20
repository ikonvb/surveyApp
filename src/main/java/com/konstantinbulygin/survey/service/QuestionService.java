package com.konstantinbulygin.survey.service;

import com.konstantinbulygin.survey.data.Question;
import com.konstantinbulygin.survey.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public void save(Question question) {
        questionRepository.save(question);
    }

    public Question findQuestionByQuestionText(String questionText) {
        return questionRepository.findByQuestionText(questionText);
    }

    public Question findById(int id) {
        return questionRepository.findById(id);
    }
}
