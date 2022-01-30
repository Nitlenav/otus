package ru.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.domain.QuestionsService;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Configuration
@PropertySource("classpath:questions.properties")
public class AskQuestionImpl implements AskQuestion {

    private final Map<String, Boolean> resultAnswer = new HashMap<>();
    private final QuestionsService questionsService;
    private final int countAnswer;

    public AskQuestionImpl(@Autowired QuestionsService questionsService,
                           @Value("${count.answer}") int countAnswer) {
        this.questionsService = questionsService;
        this.countAnswer = countAnswer;
    }

    @Override
    public Map<String, Boolean> getResult() {
        return resultAnswer;
    }

    @Override
    public void answerQuestions() {
        Random random = new Random();

        questionsService.loadQuestions();
        List<String> questionsList = questionsService.getQuestions();
        Map<String, String> answerMap = questionsService.getQuestionAnswerMap();
        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < countAnswer; i++) {
                int num = random.nextInt(questionsList.size());
                String question = questionsList.remove(num);
                String regexAnswer = answerMap.get(question);
                if (isNotBlank(regexAnswer)) {

                    System.out.println(question);
                    String answer = scanner.nextLine();

                    boolean matches = answer.matches(regexAnswer);

                    resultAnswer.put(question, matches);
                }
            }
        }
    }
}