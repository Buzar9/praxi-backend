package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.File;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class FileRepositoryImpl implements FileRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<File> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from File", File.class);
        List<File> fileList = query.getResultList();

        return fileList;
    }

    @Override
    public File findById(int fileId) {

        Session currentSession = entityManager.unwrap(Session.class);
        File tempFile = currentSession.get(File.class, fileId);

        return tempFile;
    }

    @Override
    public void saveOrUpdate(File theFile) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theFile);
    }

    @Override
    public void delete(int fileId) {

        Session currentSession = entityManager.unwrap(Session.class);
        File tempFile = currentSession.get(File.class, fileId);
        currentSession.delete(tempFile);
    }
}
