package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.Quest;

import java.util.List;

public interface QuestRepository {

    List<Quest> findAll();

    Quest findById(int questId);

    void save(Quest tempQuest, int achievementId);

    String update(Quest tempQuest, int achievementId);

    void delete(int questId);
}
