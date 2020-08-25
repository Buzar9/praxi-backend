package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.Achievement;
import com.buzar.praxibackend.entity.Quest;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class QuestRepositoryImpl implements QuestRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Quest> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Quest", Quest.class);
        List<Quest> questList = query.getResultList();

        return questList;
    }

    @Override
    public Quest findById(int questId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Quest tempQuest = currentSession.get(Quest.class, questId);

        return tempQuest;
    }

    @Override
    public void save(Quest tempQuest, int achievementId) {

//        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Achievement tempAchievement = currentSession.get(Achievement.class, achievementId);
            currentSession.save(tempQuest);
            tempAchievement.addQuest(tempQuest);
            tempQuest.setAchievementId(tempAchievement);
            tempQuest.setTitle(tempAchievement);
            currentSession.saveOrUpdate(tempAchievement);
//            return "Success";
//        } catch (ObjectOptimisticLockingFailureException exc) {
//            return "Data doesn't exist";
//        } catch (NullPointerException exc) {
//            return "Data doesn't exist";
//
//        }
    }

    @Override
    public String update(Quest tempQuest, int achievementId) {

        try {
            Session currentSession = entityManager.unwrap(Session.class);
            Achievement tempAchievement = currentSession.get(Achievement.class, achievementId);
            currentSession.update(tempQuest);
            tempAchievement.addQuest(tempQuest);
            tempQuest.setAchievementId(tempAchievement);
            tempQuest.setTitle(tempAchievement);
            currentSession.saveOrUpdate(tempAchievement);
            return "Success";
        } catch (NullPointerException exc) {
            return "Data doesn't exist";
        }
    }

    @Override
    public void delete(int questId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Quest tempQuest = currentSession.get(Quest.class, questId);
        currentSession.delete(tempQuest);
    }
}
