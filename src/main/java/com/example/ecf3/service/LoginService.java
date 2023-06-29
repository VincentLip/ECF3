package com.example.ecf3.service;

import com.example.ecf3.entity.User;

public interface LoginService {

    public boolean login(User user);
    public boolean isLogged();

    public boolean isAdmin();

    public int getUserId();

}
