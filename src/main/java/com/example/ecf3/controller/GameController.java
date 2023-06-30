package com.example.ecf3.controller;

import com.example.ecf3.entity.Game;
import com.example.ecf3.entity.GameResult;
import com.example.ecf3.entity.User;
import com.example.ecf3.exception.NotAdminException;
import com.example.ecf3.exception.NotSignInException;
import com.example.ecf3.service.GameResultService;
import com.example.ecf3.service.GameService;
import com.example.ecf3.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("game")
public class GameController {

    @Autowired
    private UserService _userService;

    @Autowired
    private GameService _gameService;

    @Autowired
    private GameResultService _gameResultService;

    @GetMapping("")
    public ModelAndView get() throws NotSignInException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game");
        modelAndView.addObject("game", _gameService.getGame());
        return modelAndView;
    }

    @GetMapping("/add")
    public String formAddGame(Model model) {
        model.addAttribute("game",new Game());
        return "add-game";
    }

    @PostMapping("/add")
    public String submitAddGame(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date dateGame, @RequestParam String userEmail1, @RequestParam String userEmail2) throws NotSignInException, IOException, NotAdminException {
        User user1 = _userService.findByEmail(userEmail1);
        User user2 = _userService.findByEmail(userEmail2);
        if(_gameService.createGame(dateGame,user1,user2)){
            return "redirect:/game";
        }
        return null;
    }

    @GetMapping("/score")
    public String formAddScore(Model model) {
        model.addAttribute("gameResult",new GameResult());
        return "add-gameResult";
    }

    @PostMapping("/score")
    public String submitAddScore(@RequestParam int score,@RequestParam int id) throws NotSignInException, IOException, NotAdminException {
        if(_gameResultService.createGameResult(score,id)){
            return "redirect:/game";
        }
        return null;
    }


}
