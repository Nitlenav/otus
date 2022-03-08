package ru.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsServiceImplTest {

    @Test
    void loadQuestions() {
        QuestionsServiceImpl questionsService = new QuestionsServiceImpl("/questions.csv", new HashMap<>());
        questionsService.loadQuestions();
        assert !questionsService.getQuestions().isEmpty();
    }
}