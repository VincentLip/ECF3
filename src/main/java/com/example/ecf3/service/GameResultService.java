package com.example.ecf3.service;

import com.example.ecf3.entity.Game;
import com.example.ecf3.entity.GameResult;
import com.example.ecf3.entity.User;
import com.example.ecf3.exception.EmptyFieldsException;
import com.example.ecf3.exception.NotAdminException;
import com.example.ecf3.exception.NotSignInException;
import com.example.ecf3.repository.GameRepository;
import com.example.ecf3.repository.GameResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GameResultService {

    @Autowired
    GameResultRepository _gameResultRepository;

    @Autowired
    GameRepository _gameRepository;
    @Autowired
    private LoginService _loginService;


    public boolean createGameResult(Integer score,Integer id) throws  NotSignInException {
        if(_loginService.isLogged()) {
                if(score == null ) {
                    EmptyFieldsException.with("score");
                }
                GameResult gameResult = GameResult.builder().score(score).build();
                _gameResultRepository.save(gameResult);
                Game game = _gameRepository.findGameById(id);
                game.setGameResult(gameResult);
                if(gameResult.getId() > 0) {
                    return true;
                }
                return false;
            }
        throw new NotSignInException();
    }

//    public int  findGameResultScores() throws NotSignInException {
//        if(_loginService.isLogged()) {
//
//            return _gameResultRepository.findGameResultScores();
//        }
//        throw new NotSignInException();
//    }

}
