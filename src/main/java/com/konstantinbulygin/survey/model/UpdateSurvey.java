package com.konstantinbulygin.survey.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UpdateSurvey {

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "End date is mandatory")
    private LocalDate endDate;

    @NotBlank(message = "Description is mandatory")
    private String description;

}
