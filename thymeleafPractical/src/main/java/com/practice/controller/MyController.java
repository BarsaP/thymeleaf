package com.practice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.practice.model.AnswerResponse;
import com.practice.model.PlayerAnswer;
import com.practice.model.Question;

@Controller
public class MyController {

    private List<Question> questions = Arrays.asList(
        new Question("What is the capital of France?", "Paris", Arrays.asList("Paris", "London", "Berlin", "Madrid")),
        new Question("Who developed Java?", "James Gosling", Arrays.asList("James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido van Rossum"))
    );
    
    private int currentQuestionIndex = 0;
    
    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping("/api/next-question")
    @ResponseBody
    public Question getNextQuestion() {
        return questions.get(currentQuestionIndex);
    }

    @MessageMapping("/game")
    @SendTo("/topic/answers")
    public void handleAnswer(PlayerAnswer playerAnswer) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        if (playerAnswer.getAnswer().equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
            template.convertAndSend("/topic/answers", new AnswerResponse("CORRECT_ANSWER", playerAnswer.getPlayerName()));
            
            //Increment index if there are more questions
            if(currentQuestionIndex < questions.size() - 1) {
            	currentQuestionIndex++;	
            }
        } else {
            template.convertAndSendToUser(playerAnswer.getPlayerName(), "/topic/answers", new AnswerResponse("INCORRECT_ANSWER", ""));
        }
    }
    
    @PostMapping("/submit-player")
    public String submitPlayer(@RequestParam("playerName") String playerName) {
    	//
        System.out.println("Player joined: " + playerName);
       //
        return "redirect:/game";
    }
    @GetMapping("/game")
    public String gamePage(Model model) {
    	if(currentQuestionIndex < questions.size()) {
    	 Question currentQuestion = questions.get(currentQuestionIndex);
         model.addAttribute("question", currentQuestion);
        // This should render the game.html page
        return "game";  
    }else {
    	return "game-over";
       }
    }
    
    @PostMapping("/submit-answer")
    public String submitAnswer(@RequestParam("answer") String answer, Model model) {
        Question currentQuestion = questions.get(currentQuestionIndex);

        if (answer.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
            model.addAttribute("result", "Correct! The answer is " + currentQuestion.getCorrectAnswer());
            
            //Only increment if there are more questions
            if(currentQuestionIndex < questions.size() - 1) {
            	currentQuestionIndex++;
            }else {
            	return "game-over";
            }
            
        } else {
            model.addAttribute("result", "Incorrect. The correct answer was " + currentQuestion.getCorrectAnswer());
        }

        // Pass the next question for the user to answer
        if (currentQuestionIndex < questions.size()) {
            model.addAttribute("question", questions.get(currentQuestionIndex));
            return "game"; 
        } else {
            return "game-over"; 
        }
    }
}
