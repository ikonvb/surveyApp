package com.konstantinbulygin.survey.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Embeddable
public class SurveyQuestionKey implements Serializable {

    private static final long serialVersionUID = 2L;

    @Column(name = "survey_id")
    private int surveyId;

    @Column(name = "question_id")
    private int questionId;

}
