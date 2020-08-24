package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.File;
import com.buzar.praxibackend.entity.Quest;
import com.buzar.praxibackend.entity.Realization;
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
public class RealizationRepositoryImpl implements RealizationRepository{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Realization> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Realization", Realization.class);
        List<Realization> realizationList = query.getResultList();

        return realizationList;
    }

    @Override
    public Realization findById(int realizationId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Realization tempRealization = currentSession.get(Realization.class, realizationId);

        return tempRealization;
    }

    @Override
    public void saveOrUpdate(Realization tempRealization, int userId, int questId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Quest tempQuest = currentSession.get(Quest.class, questId);
        User tempUser = currentSession.get(User.class, userId);

        tempRealization.setQuestId(tempQuest);
        tempRealization.setUserId(tempUser);

        currentSession.saveOrUpdate(tempRealization);
    }

    @Override
    public void addFile(File tempFile) {

    }

    @Override
    public void addRelationQuestReal(int realId, int questId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Quest tempQuest = currentSession.get(Quest.class, questId);
        Realization tempRealization = currentSession.get(Realization.class, realId);

        tempQuest.addRealization(tempRealization);
    }

    @Override
    public void setRealizationData(int realId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Realization tempRealization = currentSession.get(Realization.class, realId);

        tempRealization.setUserId(tempRealization.getUserId());
        tempRealization.setUsername(tempRealization.getUserId());
    }

    @Override
    public void addFile(int realId, int fileId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Realization tempRealization = currentSession.get(Realization.class, realId);
        Query query = currentSession.createQuery("from File", File.class);
        List<File> fileList = query.getResultList();
        File tempFile = currentSession.get(File.class, fileId);

        tempRealization.setImagePath(tempFile.getFileName());
        tempRealization.addFile(tempFile);
    }

    @Override
    public void delete(int realizationId) {

        Session currentSession = entityManager.unwrap(Session.class);
        Realization tempRealization = currentSession.get(Realization.class, realizationId);
        currentSession.delete(tempRealization);
    }

    @Override
    public void removeRelationQuestReal(int realId) {

        Session currentSession = entityManager.unwrap(Session.class);

        Realization tempRealization = currentSession.get(Realization.class, realId);
        Quest tempQuest = currentSession.get(Quest.class, tempRealization.getQuestId());

        tempQuest.removeRealization(tempRealization);
    }
}
