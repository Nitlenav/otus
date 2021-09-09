package ru;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.answer.AskQuestion;
import ru.domain.QuestionsService;
import ru.result.DisplayResult;

@Configuration
@ComponentScan
@PropertySource("classpath:questions.properties")
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        QuestionsService questionsService = context.getBean(QuestionsService.class);
        AskQuestion askQuestion = context.getBean(AskQuestion.class);
        DisplayResult displayResult = context.getBean(DisplayResult.class);
    }
}
