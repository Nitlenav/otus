package ru.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Configuration
@PropertySource("classpath:questions.properties")
public class QuestionsServiceImpl implements QuestionsService {

    private final String path;
    private final Map<String, String> answerMap;
    private final Map<String, String> questionAnswerMap = new HashMap<>();
    private final List<String> questions = new ArrayList<>();;


    public QuestionsServiceImpl(@Value("${path.questions.csv}") String path,
                                @Value("#{${valuesMap}}") Map<String, String> answerMap) {
        this.path = path;
        this.answerMap = answerMap;
    }

    @Override
    public void loadQuestions() {
        String regex = "[() -?',]";

        if (isNotBlank(path)) {
            ClassPathResource resource = new ClassPathResource(path);
            List<String> questions = getQuestions();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {

                reader.lines().forEach(val -> {
                    questions.add(val);
                    String s = val.toLowerCase(Locale.ROOT).replaceAll(regex, EMPTY);
                    String value = answerMap.get(s);
                    if (isNotBlank(value)) {
                        questionAnswerMap.put(val, value);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> getQuestions() {
        return this.questions;
    }

    @Override
    public Map<String, String> getQuestionAnswerMap() {
        return this.questionAnswerMap;
    }
}
