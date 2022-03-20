package com.konstantinbulygin.survey.controllers;

import com.konstantinbulygin.survey.data.*;
import com.konstantinbulygin.survey.service.QuestionService;
import com.konstantinbulygin.survey.service.SurveyQuestionService;
import com.konstantinbulygin.survey.service.SurveyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api("Main Controller")
public class SurveyApiController {

    @Autowired
    SurveyService surveyService;

    @Autowired
    QuestionService questionService;

    @Autowired
    SurveyQuestionService surveyQuestionService;

    @ApiOperation("Show all question for particular survey, returns list")
    @GetMapping(value = "/show/survey/{surveyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> showOneSurvey(@PathVariable int surveyId) {
        //retrieve all friends for current client
        List<Integer> questIds = surveyQuestionService.findAllQuestionIdForSurvey(surveyId);

        System.out.println(questIds);

        List<Question> questionList = new ArrayList<>();

        for (Integer id : questIds) {
            questionList.add(questionService.findById(id));
        }
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @ApiOperation("Show all surveys, returns list")
    @GetMapping(value = "/survey/find/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Survey>> findAllSurvey() {
        //retrieve all friends for current client
        LocalDate localDate = LocalDate.now();
        List<Survey> surveys = surveyService.findAllSurveyByStartDate(localDate);
        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }

    @ApiOperation("Adds question to survey, returns string")
    @PostMapping(path = "/admin/add/question/survey/{surveyId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addQuestionToSurvey(
            @PathVariable int surveyId,
            @Valid @RequestBody Question quest) {

        questionService.save(quest);
        Optional<Survey> optSurvey = surveyService.findById(surveyId);
        Question question = questionService.findQuestionByQuestionText(quest.getQuestionText());

        if (optSurvey.isPresent()) {
            Survey survey = optSurvey.get();
            SurveyQuestionKey key = new SurveyQuestionKey();
            key.setSurveyId(survey.getId());
            key.setQuestionId(question.getId());
            SurveyQuestion surveyQuestion = new SurveyQuestion();
            surveyQuestion.setKey(key);
            surveyQuestionService.save(surveyQuestion);
        }
        return "ok";
    }

    @ApiOperation("Adds survey, returns string")
    @PostMapping(path = "/admin/add/survey", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createSurvey(@Valid @RequestBody Survey sur) {
        Survey survey = new Survey();
        survey.setName(sur.getName());
        survey.setStartDate(LocalDate.now());
        survey.setDescription(sur.getDescription());
        surveyService.save(survey);
        return "ok";
    }

    @ApiOperation("Edit survey, edited survey")
    @PatchMapping(path = "/admin/edit/survey/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Survey> updateSurvey(
            @PathVariable int id,
            @Valid @RequestBody UpdateSurvey updateSurvey
    ) {
        Optional<Survey> optSurvey = surveyService.findById(id);
        if (optSurvey.isPresent()) {
            Survey survey = optSurvey.get();
            survey.setName(updateSurvey.getName());
            survey.setEndDate(updateSurvey.getEndDate());
            survey.setDescription(updateSurvey.getDescription());
            surveyService.save(survey);
            return new ResponseEntity<>(optSurvey.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @ApiOperation("Delete one survey, returns HttpStatus.NO_CONTENT")
    @DeleteMapping(path = "/admin/delete/survey/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSurvey(@PathVariable int id) {
        try {
            Optional<Survey> survey = surveyService.findById(id);
            survey.ifPresent(value -> surveyService.delete(value));
        } catch (EmptyResultDataAccessException err) {
            err.printStackTrace();
        }
    }
}
