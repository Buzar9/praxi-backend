package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.Achievement;

import java.util.List;

public interface AchievementRepository {

    List<Achievement> findAll();

    Achievement findById(int achievementId);

    void saveOrUpdate(Achievement tempAchievement);

    String addAchievementToUser(int achievementId, int userId);

    String removeAchievementFromUser(int achievementId, int userId);

    void finishAchievementForUser(int achievementId, int userId);

    void takeAchievementFromUser(int achievementId, int userId);

    void delete(int achievementId);
}
