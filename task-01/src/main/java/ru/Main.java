package ru;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.domain.Questions;
import ru.domain.QuestionsServiceImpl;

public class Main {
    public static void main(String[] args) {
        // TODO: создайте здесь класс контекста
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        Questions service = context.getBean(QuestionsServiceImpl.class);
        service.getQuestions().forEach(System.out::println);
    }
}
