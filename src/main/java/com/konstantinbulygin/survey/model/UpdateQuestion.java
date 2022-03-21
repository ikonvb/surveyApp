package com.konstantinbulygin.survey.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UpdateQuestion {

    @NotBlank(message = "questionId is mandatory")
    Integer questionId;

    @NotBlank(message = "surveyId is mandatory")
    Integer surveyId;

    @NotBlank(message = "loginName is mandatory")
    String loginName;

    @NotBlank(message = "Text question is mandatory")
    String questionText;

    @NotBlank(message = "Question type is mandatory")
    QuestionType questionType;
}
