package com.konstantinbulygin.survey.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "survey_question")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SurveyQuestion {

    static final long serialVersionUID = 1L;

    @EmbeddedId
    private SurveyQuestionKey key;

}
