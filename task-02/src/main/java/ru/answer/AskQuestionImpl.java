package ru.answer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.domain.Questions;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Configuration
@PropertySource("classpath:questions.properties")
public class AskQuestionImpl implements AskQuestion {

    private final Map<String, Boolean> resultAnswer = new HashMap<>();

    public AskQuestionImpl(Questions questions,
                           @Value("${count.answer}") int counter){
        Random random = new Random();
        List<String> questionsList = questions.getQuestions();
        Map<String, String> answerMap = questions.getQuestionAnswerMap();
        try(Scanner scanner = new Scanner(System.in)){
            for (int i = 0; i < counter; i++) {
                int num = random.nextInt(questionsList.size());
                String question = questionsList.remove(num);
                String regexAnswer = answerMap.get(question);
                if (isNotBlank(regexAnswer)){

                    System.out.println(question);
                    String answer =  scanner.nextLine();

                    boolean matches = answer.matches(regexAnswer);

                    resultAnswer.put(question, matches);
                }
            }
        }
    }


    @Override
    public Map<String, Boolean> getResult() {
        return resultAnswer;
    }
}
