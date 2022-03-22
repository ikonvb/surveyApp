package com.konstantinbulygin.survey.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "answers")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "client_id is mandatory")
    @Column(name = "client_id")
    private Integer clientId;

    @NotBlank(message = "survey_id is mandatory")
    @Column(name = "survey_id")
    private Integer surveyId;

    @NotBlank(message = "question_id is mandatory")
    @Column(name = "question_id")
    private Integer questionId;

    @NotBlank(message = "answer_text is mandatory")
    @Column(name = "answer_text")
    String answerText;

    @NotBlank(message = "one_choice_answer is mandatory")
    @Column(name = "one_choice_answer")
    Integer oneChoiceAnswer;

    @NotBlank(message = "multi_answers is mandatory")
    @Column(name = "multi_answer")
    String multiAnswers;
}
