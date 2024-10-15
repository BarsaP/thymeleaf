package com.practice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.model.AnswerResponse;
import com.practice.model.PlayerAnswer;
import com.practice.model.Question;

@RestController
public class MyController {

    private List<Question> questions = Arrays.asList(
        new Question("What is the capital of France?", "Paris"),
        new Question("Who developed Java?", "James Gosling")
    );
    
    private int currentQuestionIndex = 0;
    
    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/api/next-question")
    public Question getNextQuestion() {
        return questions.get(currentQuestionIndex);
    }

    @MessageMapping("/game")
    @SendTo("/topic/answers")
    public void handleAnswer(PlayerAnswer playerAnswer) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (playerAnswer.getAnswer().equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
            template.convertAndSend("/topic/answers", new AnswerResponse("CORRECT_ANSWER", playerAnswer.getPlayerName()));
            currentQuestionIndex++;
        } else {
            template.convertAndSendToUser(playerAnswer.getPlayerName(), "/topic/answers", new AnswerResponse("INCORRECT_ANSWER", ""));
        }
    }
}
