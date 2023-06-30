package com.example.ecf3.controller;

import com.example.ecf3.exception.NotSignInException;
import com.example.ecf3.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private GameService _gameService;
    @GetMapping("")
    public ModelAndView get() throws NotSignInException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("game", _gameService.getGame());

        return modelAndView;
    }
}
