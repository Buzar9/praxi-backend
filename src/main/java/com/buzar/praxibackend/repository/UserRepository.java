package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository{

    List<User> findAll();

    User findByUserId(int userId);

    Optional<User> findByUsername(String tempUsername);

    void saveOrUpdate(User user);

    void deleteUser(int userId);
}
