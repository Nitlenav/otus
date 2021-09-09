package ru.domain;

import java.util.List;
import java.util.Map;

public interface Questions {

    List<String> getQuestions();

    Map<String, String> getQuestionAnswerMap();
}
