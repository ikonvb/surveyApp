package com.konstantinbulygin.survey.controllers;

import com.konstantinbulygin.survey.model.*;
import com.konstantinbulygin.survey.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Api("Main Controller")
public class SurveyApiController {

    @Autowired
    RoleService roleService;

    @Autowired
    ClientService clientService;

    @Autowired
    SurveyService surveyService;

    @Autowired
    QuestionService questionService;

    @Autowired
    SurveyQuestionService surveyQuestionService;

    //done
    @ApiOperation("Register client, returns Client")
    @PostMapping(path = "/register/client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Client registerClient(@Valid @RequestBody Client jsonClient) {
        Client client = new Client();
        client.setClientName(jsonClient.getClientName());
        client.setPassword(jsonClient.getPassword());
        client.setStatus(jsonClient.getStatus());
        client.setRoleId(jsonClient.getRoleId());
        client.setCreated(LocalDate.now());
        client.setUpdated(LocalDate.now());
        clientService.save(client);
        return client;
    }

    //done
    @ApiOperation("Check authorities by name, returns role")
    @GetMapping(value = "/check/{loginName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> checkAuthentication(@PathVariable String loginName) {

        Client client = clientService.findByClientName(loginName);
        Role role = roleService.findById(client.getRoleId()).orElse(null);
        if (role != null) {
            if (role.getName().equals("ROLE_USER")) {
                return new ResponseEntity<>(role, HttpStatus.OK);
            } else if (role.getName().equals("ROLE_ADMIN")) {
                return new ResponseEntity<>(role, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //done
    @ApiOperation("Show all question for particular survey, returns list")
    @GetMapping(value = "/show/survey/{surveyId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> showOneSurvey(@PathVariable int surveyId) {
        List<Integer> questIds = surveyQuestionService.findAllQuestionIdForSurvey(surveyId);
        List<Question> questionList = new ArrayList<>();
        for (Integer id : questIds) {
            questionList.add(questionService.findById(id));
        }
        if (questionList.size() > 0) {
            return new ResponseEntity<>(questionList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    //done
    @ApiOperation("Show all surveys, returns list")
    @GetMapping(value = "/survey/find/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Survey>> findAllSurvey() {
        LocalDate localDate = LocalDate.now();
        List<Survey> surveys = surveyService.findAllSurveyByStartDate(localDate);
        return new ResponseEntity<>(surveys, HttpStatus.OK);
    }

    //done
    @ApiOperation("Adds question to survey, returns string")
    @PostMapping(path = "/add/question/{surveyId}/{loginName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addQuestionToSurvey(
            @PathVariable int surveyId,
            @PathVariable String loginName,
            @Valid @RequestBody Question quest) {

        Client client = clientService.findByClientName(loginName);
        Role role = roleService.findById(client.getRoleId()).orElse(null);

        if (role != null) {
            if (role.getName().equals("ROLE_USER")) {
                return new ResponseEntity<>("You don`t have a permission to add question", HttpStatus.FORBIDDEN);
            } else if (role.getName().equals("ROLE_ADMIN")) {
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
                return new ResponseEntity<>("Question added to survey", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    //done
    @ApiOperation("Adds survey if it allowed to current user, returns string")
    @PostMapping(path = "/add/survey/{loginName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createSurvey(@PathVariable String loginName, @Valid @RequestBody Survey sur) {

        Client client = clientService.findByClientName(loginName);
        Role role = roleService.findById(client.getRoleId()).orElse(null);
        if (role != null) {
            if (role.getName().equals("ROLE_USER")) {
                return new ResponseEntity<>("You don`t have a permission to add survey", HttpStatus.FORBIDDEN);
            } else if (role.getName().equals("ROLE_ADMIN")) {
                Survey survey = new Survey();
                survey.setName(sur.getName());
                survey.setStartDate(LocalDate.now());
                survey.setDescription(sur.getDescription());
                surveyService.save(survey);
                return new ResponseEntity<>("Survey added", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //done
    @ApiOperation("Edit question")
    @PatchMapping(path = "/edit/question", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> updateQuestion(@Valid @RequestBody UpdateQuestion updateQuestion) {

        Client client = clientService.findByClientName(updateQuestion.getLoginName());
        Role role = roleService.findById(client.getRoleId()).orElse(null);

        if (role != null) {
            if (role.getName().equals("ROLE_USER")) {
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            } else if (role.getName().equals("ROLE_ADMIN")) {

                List<Integer> questIds = surveyQuestionService.findAllQuestionIdForSurvey(updateQuestion.getSurveyId());
                Question question = new Question();
                for (Integer i : questIds) {
                    if (Objects.equals(i, updateQuestion.getQuestionId())) {
                        question = questionService.findById(i);
                    }
                }
                question.setQuestionText(updateQuestion.getQuestionText());
                question.setQuestionType(updateQuestion.getQuestionType());
                questionService.save(question);
                return new ResponseEntity<>(question, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //done
    @ApiOperation("Edit survey")
    @PatchMapping(path = "/edit/survey/{id}/{loginName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Survey> updateSurvey(
            @PathVariable int id,
            @PathVariable String loginName,
            @Valid @RequestBody UpdateSurvey updateSurvey
    ) {
        Client client = clientService.findByClientName(loginName);
        Role role = roleService.findById(client.getRoleId()).orElse(null);
        if (role != null) {
            if (role.getName().equals("ROLE_USER")) {
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            } else if (role.getName().equals("ROLE_ADMIN")) {
                Optional<Survey> optSurvey = surveyService.findById(id);
                if (optSurvey.isPresent()) {
                    Survey survey = optSurvey.get();
                    survey.setName(updateSurvey.getName());
                    survey.setEndDate(updateSurvey.getEndDate());
                    survey.setDescription(updateSurvey.getDescription());
                    surveyService.save(survey);
                    return new ResponseEntity<>(survey, HttpStatus.OK);
                }
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //done
    @ApiOperation("Delete one survey, returns HttpStatus.NO_CONTENT")
    @DeleteMapping(path = "/delete/survey/{id}/{loginName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteSurvey(@PathVariable int id, @PathVariable String loginName) {

        Client client = clientService.findByClientName(loginName);
        Role role = roleService.findById(client.getRoleId()).orElse(null);

        if (role != null) {
            if (role.getName().equals("ROLE_USER")) {
                return new ResponseEntity<>("You don`t have a permission to delete", HttpStatus.FORBIDDEN);
            } else if (role.getName().equals("ROLE_ADMIN")) {
                Optional<Survey> survey = surveyService.findById(id);
                survey.ifPresent(value -> surveyService.delete(value));
                return new ResponseEntity<>("Survey deleted", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);

    }

    //done
    @ApiOperation("Delete one question, returns HttpStatus.NO_CONTENT")
    @DeleteMapping(path = "/delete/question/{loginName}/{surveyId}/{questionId}")
    public ResponseEntity<String> deleteQuestion(
            @PathVariable String loginName,
            @PathVariable int surveyId,
            @PathVariable int questionId) {

        Client client = clientService.findByClientName(loginName);
        Role role = roleService.findById(client.getRoleId()).orElse(null);

        if (role != null) {
            if (role.getName().equals("ROLE_USER")) {
                return new ResponseEntity<>("You don`t have a permission to delete question", HttpStatus.FORBIDDEN);
            } else if (role.getName().equals("ROLE_ADMIN")) {

                List<Integer> questIds = surveyQuestionService.findAllQuestionIdForSurvey(surveyId);

                for (Integer i : questIds) {
                    if (Objects.equals(i, questionId)) {
                        SurveyQuestionKey key = new SurveyQuestionKey();
                        key.setSurveyId(surveyId);
                        key.setQuestionId(questionId);
                        SurveyQuestion surveyQuestion = new SurveyQuestion(key);
                        surveyQuestionService.delete(surveyQuestion);
                        Question question = questionService.findById(i);
                        questionService.delete(question);
                        return new ResponseEntity<>("Question has been deleted", HttpStatus.NO_CONTENT);
                    }
                }
            }
        }
        return new ResponseEntity<>("Client has not been found", HttpStatus.NOT_FOUND);
    }
}
