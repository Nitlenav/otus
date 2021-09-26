package ru;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.answer.AskQuestion;
import ru.answer.AskQuestionImpl;
import ru.domain.QuestionsService;
import ru.result.DisplayResult;
import ru.result.DisplayResultImpl;

import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan
@PropertySource("classpath:questions.properties")
public class Main {

    public static void main(String[] args) {
        // TODO: создайте здесь класс контекста
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        
        QuestionsService questionsService = context.getBean(QuestionsService.class);
        questionsService.loadQuestions();

        AskQuestion askQuestion = context.getBean(AskQuestion.class);
        askQuestion.answerQuestions();

        DisplayResult displayResult = context.getBean(DisplayResult.class);
        displayResult.showResult();
    }
}
