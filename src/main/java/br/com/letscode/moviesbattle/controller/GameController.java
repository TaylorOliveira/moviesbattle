package br.com.letscode.moviesbattle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @GetMapping
    @ResponseBody
    public String getQuiz() {
        return "Next quiz";
    }
}