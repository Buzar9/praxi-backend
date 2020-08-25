package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.User;

import java.util.List;

public interface UserRepository{

    List<User> findAll();

    User findByUserId(int userId);

    void save(User user);

    String update (User user);

    void deleteUser(int userId);
}
