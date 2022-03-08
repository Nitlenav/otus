package ru.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.answer.AskQuestion;
import java.util.Map;

@Component
@ComponentScan("classpath:questions.properties")
public class DisplayResultImpl implements DisplayResult {

    private final AskQuestion askQuestion;
    private int correctAnswer;

    public DisplayResultImpl(@Autowired  AskQuestion askQuestion,
                             @Value("${correct.answer}") int correctAnswer) {
        this.askQuestion = askQuestion;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public void showResult() {
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
        }else {
            System.out.println("Exam passed successfully .)) \nAll answers.");
            answerMap.forEach((k, v) -> System.out.println(k +" - " + v));
        }
    }
}
