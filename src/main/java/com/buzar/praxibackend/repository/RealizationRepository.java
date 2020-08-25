package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.File;
import com.buzar.praxibackend.entity.Realization;

import java.util.List;

public interface RealizationRepository {

    List<Realization> findAll();

    Realization findById(int realizationId);

    String save(Realization tempRealization, int userId, int questId);

    String update(Realization tempRealization, int userId, int questId);

    void addFile(File tempFile);

    String addRelationQuestReal(int realId, int questId);

    String setRealizationData(int realId);

    void addFile(int realId, int fileId);

    void delete(int realizationId);

    void removeRelationQuestReal(int realId);
}
