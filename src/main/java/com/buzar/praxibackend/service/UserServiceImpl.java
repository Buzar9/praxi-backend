package com.buzar.praxibackend.service;

import com.buzar.praxibackend.entity.User;
import com.buzar.praxibackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepositoryImpl;

    @Override
    @Transactional
    public void addUser(User tempUser) {

        tempUser.setUserId(0);
        userRepositoryImpl.saveOrUpdate(tempUser);
    }

    @Override
    @Transactional
    public void updateUser(int userId, User tempUser) {

        tempUser.setUserId(userId);
        userRepositoryImpl.saveOrUpdate(tempUser);
    }
}
