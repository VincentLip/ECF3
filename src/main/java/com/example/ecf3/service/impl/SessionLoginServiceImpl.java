package com.example.ecf3.service.impl;


import com.example.ecf3.entity.User;
import com.example.ecf3.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionLoginServiceImpl implements LoginService {

    @Autowired
    HttpSession httpSession;

    @Override
    public boolean login(User user) {
        httpSession.setAttribute("isLogged", true);
        httpSession.setAttribute("fullName", user.getFirstName() + " "+user.getLastName());
        httpSession.setAttribute("isAdmin", user.isAdmin());
        httpSession.setAttribute("userId", user.getId());
        return true;
    }

    @Override
    public boolean isLogged() {
        return httpSession.getAttribute("isLogged") != null && (boolean)httpSession.getAttribute("isLogged") == true;
    }

    @Override
    public boolean isAdmin() {
        return httpSession.getAttribute("isLogged") != null && (boolean)httpSession.getAttribute("isAdmin") == true;
    }

    @Override
    public int getUserId() {
        return (int)httpSession.getAttribute("userId");
    }


}
