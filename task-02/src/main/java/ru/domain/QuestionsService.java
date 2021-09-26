package ru.domain;

import java.util.List;
import java.util.Map;

public interface QuestionsService {

    void loadQuestions();

    List<String> getQuestions();

    Map<String, String> getQuestionAnswerMap();
}
