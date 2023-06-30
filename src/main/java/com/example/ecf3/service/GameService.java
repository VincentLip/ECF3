package com.example.ecf3.service;

import com.example.ecf3.entity.Game;
import com.example.ecf3.entity.User;
import com.example.ecf3.exception.EmptyFieldsException;
import com.example.ecf3.exception.GameNotExistException;
import com.example.ecf3.exception.NotAdminException;
import com.example.ecf3.exception.NotSignInException;
import com.example.ecf3.repository.GameRepository;
import com.example.ecf3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository _gameRepository;

    @Autowired
    private LoginService _loginService;

    @Autowired
    private UserRepository _userRepository;


    public boolean createGame(Date date, User user1 , User user2) throws NotAdminException, NotSignInException, IOException {
        if(_loginService.isLogged()) {
            if(_loginService.isAdmin()) {
                if(date == null || user1 == null || user2 == null) {
                    EmptyFieldsException.with("date", "user1", "user2");
                }
                Game game = Game.builder().gameDate(date).user1(user1).user2(user2).build();
                _gameRepository.save(game);
                if(game.getId() > 0) {
                    return true;
                }
                return false;
            }
            throw new NotAdminException();
        }
        throw new NotSignInException();
    }


    public List<Game> getGame() throws NotSignInException {
        if(_loginService.isLogged()) {
            return (List<Game>) _gameRepository.findAll();
        }
        throw new NotSignInException();
    }

    public Game findGameById(int id) throws NotSignInException {
        if(_loginService.isLogged()) {
            return _gameRepository.findGameById(id);
        }
        throw new NotSignInException();
    }

    public Game getGameById(int id) throws NotSignInException {
        if(_loginService.isLogged()) {
            return _gameRepository.getGameById(id);
        }
        throw new NotSignInException();
    }


}
