package ru.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.answer.AskQuestionImpl;
import ru.domain.QuestionsServiceImpl;

import java.util.Map;


@Configuration
@PropertySource("classpath:questions.properties")
public class DisplayResultImpl implements DisplayResult {

    public DisplayResultImpl(AskQuestionImpl askQuestion,
                             @Value("${correct.answer}") int correctAnswer) {
        Map<String, Boolean> answerMap = askQuestion.getResult();

        for (Map.Entry<String, Boolean> entry : answerMap.entrySet()) {
            if (entry.getValue()) {
                --correctAnswer;
            }
        }
        if (correctAnswer > 0){
            System.out.println("Exam failed :(( \nWrong answers.");
            answerMap.forEach((k, v) -> {
                if (!v){
                    System.out.println(k +" - " + v);
                }
            });
        }
    }
}
