package com.konstantinbulygin.survey.repository;

import com.konstantinbulygin.survey.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findByQuestionText(String questionText);

    Question findById(int id);
}

