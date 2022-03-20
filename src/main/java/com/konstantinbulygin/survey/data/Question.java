package com.konstantinbulygin.survey.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "questions")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer id;

    @NotBlank(message = "Text question is mandatory")
    @Column(name = "question_text")
    String questionText;

    @NotBlank(message = "Question type is mandatory")
    @Column(name = "question_type")
    @Enumerated(EnumType.STRING)
    QuestionType questionType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(questionText, question.questionText) && questionType == question.questionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, questionType);
    }
}
