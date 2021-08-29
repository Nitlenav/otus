package ru.domain;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class QuestionsServiceImpl implements Questions {

    private List<String> questions;

    public QuestionsServiceImpl() {
    }

    public QuestionsServiceImpl(String path) {
        if (isNotBlank(path)) {
            ClassPathResource resource = new ClassPathResource(path);
            List<String> questions = getQuestions();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                reader.lines().forEach(questions::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> getQuestions() {
        if (questions == null) {
            this.questions = new ArrayList<>();
        }
        return this.questions;
    }
}
