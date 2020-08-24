package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.User;

import java.util.List;

public interface UserRepository{

    List<User> findAll();

    User findByUserId(int userId);

    void saveOrUpdate(User user);

    void deleteUser(int userId);
}
