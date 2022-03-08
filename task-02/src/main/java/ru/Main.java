package ru;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.answer.AskQuestion;
import ru.domain.Questions;

import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        // TODO: создайте здесь класс контекста
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        Questions questions = context.getBean(Questions.class);
        AskQuestion askQuestion = context.getBean(AskQuestion.class);

        List<String> questionsList = questions.getQuestions();
        Map<String, String> questionAnswerMap = questions.getQuestionAnswerMap();

//        questionsList.forEach(System.out::println);
    }
}
