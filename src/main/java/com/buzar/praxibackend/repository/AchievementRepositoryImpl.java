package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.Achievement;
import com.buzar.praxibackend.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class AchievementRepositoryImpl implements AchievementRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Achievement> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Achievement", Achievement.class);
        List<Achievement> achievementList = query.getResultList();

        return achievementList;
    }

    @Override
    public Achievement findById(int achievementId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Achievement tempAchievement = currentSession.get(Achievement.class, achievementId);

        return tempAchievement;
    }


    @Override
    public void saveOrUpdate(Achievement tempAchievement) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(tempAchievement);
    }

    @Override
    public String addAchievementToUser(int achievementId, int userId) {

        try {
            Session currentSession = entityManager.unwrap(Session.class);
            User tempUser = currentSession.get(User.class, userId);
            Achievement tempAchievement = currentSession.get(Achievement.class, achievementId);
            tempUser.addOpenAch(tempAchievement);
            currentSession.saveOrUpdate(tempUser);
            currentSession.saveOrUpdate(tempAchievement);
            return "Success";
        } catch (NullPointerException exc) {
            return "Data doesn't exist";
        }
    }

    @Override
    public String removeAchievementFromUser(int achievementId, int userId) {

        try {
            Session currentSession = entityManager.unwrap(Session.class);
            User tempUser = currentSession.get(User.class, userId);
            Achievement tempAchievement = currentSession.get(Achievement.class, achievementId);
            tempUser.removeOpenAch(tempAchievement);
            currentSession.saveOrUpdate(tempUser);
            currentSession.saveOrUpdate(tempAchievement);
            return "Success";
        } catch (NullPointerException exc) {
            return "Data doesn't exist";
        }

    }

    @Override
    public void finishAchievementForUser(int achievementId, int userId) {

        Session currentSession = entityManager.unwrap(Session.class);
        User tempUser = currentSession.get(User.class, userId);
        Achievement tempAchievement = currentSession.get(Achievement.class, achievementId);
        tempUser.addFinishedAch(tempAchievement);
        tempUser.removeOpenAch(tempAchievement);
        currentSession.saveOrUpdate(tempUser);
        currentSession.saveOrUpdate(tempAchievement);
    }

    @Override
    public void takeAchievementFromUser(int achievementId, int userId) {

        Session currentSession = entityManager.unwrap(Session.class);
        User tempUser = currentSession.get(User.class, userId);
        Achievement tempAchievement = currentSession.get(Achievement.class, achievementId);
        tempUser.removeFinishedAch(tempAchievement);
        currentSession.saveOrUpdate(tempUser);
        currentSession.saveOrUpdate(tempAchievement);
    }

    @Override
    public void delete(int achievementId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Achievement tempAchievement = currentSession.get(Achievement.class, achievementId);
        currentSession.delete(tempAchievement);
    }
}

