package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.Realization;

import java.util.List;

public interface RealizationRepository {

    List<Realization> findAll();

    Realization findById(int realizationId);

    void saveOrUpdate(Realization tempRealization, int userId, int questId);

    void addFile(File tempFile);

    void addRelationQuestReal(int realId, int questId);

    void setRealizationData(int realId);

    void addFile(int realId, int fileId);

    void delete(int realizationId);

    void removeRelationQuestReal(int realId);
}
