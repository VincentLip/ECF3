package com.example.ecf3.controller;

import com.example.ecf3.exception.*;
import com.example.ecf3.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService _userService;
    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession _httpSession;


    @GetMapping("signin")
    public ModelAndView signIn() {
        ModelAndView modelAndView = new ModelAndView("signin");
        return modelAndView;
    }

    @PostMapping("signin")
    public String signUp(@RequestParam String email, @RequestParam String password) throws UserNotExistException, IOException {
        if(_userService.signIn(email, password)) {
            return "redirect:/user/profil";
        }
        return null;
    }

    @ExceptionHandler(UserNotExistException.class)
    public ModelAndView handleUserNotExist(UserNotExistException ex) {
        ModelAndView modelAndView = new ModelAndView("signin");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @GetMapping("signup")
    public ModelAndView postSignIn() {
        ModelAndView modelAndView = new ModelAndView("signup");
        return modelAndView;
    }

    @PostMapping("signup")
    public String postSignUp(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) throws UserExistException, IOException, UserExistException {
        if(_userService.signUp(firstName, lastName, email, password)) {
            return "redirect:/user/signin";
        }
        return null;
    }

    @ExceptionHandler(UserExistException.class)
    public ModelAndView handleUserExist(UserExistException ex) {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView profil() {
        int userId = (int) _httpSession.getAttribute("userId");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("profil");
        modelAndView.addObject("user", _userService.getById(userId));
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView formEditUser(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("signup");
        mv.addObject("user", _userService.getById(id));
        return mv;
    }

    @PostMapping("/edit/{id}")
    public String submitFormEditUser(@PathVariable int id, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password) throws NotSignInException, UserNotExistException, EmptyFieldsException, NotAdminException {
        if(_userService.updateUser(id, firstName,lastName,email,password)){
            return "redirect:/";
        }
        return null;
    }


    @GetMapping("logout")
    public ModelAndView getLogout() {
        _userService.logout();
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

}
