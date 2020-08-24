package com.buzar.praxibackend.service;

import com.buzar.praxibackend.entity.User;

public interface UserService {

    void addUser(User tempUser);

    void updateUser(int userId, User tempUser);
}
