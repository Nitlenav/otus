package ru.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.domain.QuestionsServiceImpl;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс QuestionsServiceImpl")
public class QuestionsServiceImplTest {

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() throws IOException {
        QuestionsServiceImpl questionsService0 = new QuestionsServiceImpl();
        assertEquals(0, questionsService0.getQuestions().size());

        QuestionsServiceImpl questionsService1 = new QuestionsServiceImpl("/questions.csv");
        assertEquals(100, questionsService1.getQuestions().size());
    }
}
