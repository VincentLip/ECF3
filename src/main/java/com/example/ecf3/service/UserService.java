package com.example.ecf3.service;

import com.example.ecf3.entity.User;
import com.example.ecf3.exception.*;
import com.example.ecf3.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private LoginService _loginService;

    @Autowired
    private HttpSession _httpSession;

    public boolean signUp(String firstName, String lastName, String email, String password) throws UserExistException {
        try {
            _userRepository.findByEmail(email);
            throw new UserExistException();
        }
        catch (Exception ex) {
            User user = User.builder().firstName(firstName).lastName(lastName).email(email).password(password).build();
            _userRepository.save(user);
            return user.getId() > 0;
        }
    }

    public boolean updateUser(int id, String firstName, String lastName, String email, String password) throws EmptyFieldsException, NotSignInException, UserNotExistException {
        if(_loginService.isLogged()) {
                if(firstName != null || lastName != null || email != null || password != null) {
                    try {
                        User user = _userRepository.findById(id).get();
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        user.setEmail(email);
                        user.setPassword(password);
                        _userRepository.save(user);
                        return true;
                    }catch (Exception ex) {
                        throw new UserNotExistException();
                    }

                }
                throw EmptyFieldsException.with("firstName","lastName","email","password");
        }
        throw new NotSignInException();
    }


    public boolean signIn(String email, String password) throws UserNotExistException {
        try {
            User user = _userRepository.findByEmailAndPassword(email, password);
            return _loginService.login(user);
        }catch (Exception ex) {
            throw new UserNotExistException();
        }
    }

    public List<User> getUsers() throws NotSignInException, NotAdminException {
        if(_loginService.isLogged()) {
            if(_loginService.isAdmin()) {
                return (List<User>)_userRepository.findAll();
            }
            throw new NotAdminException();
        }
        throw new NotSignInException();
    }
    public User getById(int id){
        return _userRepository.getById(id);
    }

    public User findByEmail(String email){
        return _userRepository.findByEmail(email);
    }


    public boolean isLogged() {
        try {
            boolean attrIsLogged = (boolean) _httpSession.getAttribute("isLogged");
            return attrIsLogged;
        }catch (Exception ex) {
            return false;
        }
    }

    public boolean logout(){
        _httpSession.setAttribute("isLogged", false);
        return true;
    }




}
