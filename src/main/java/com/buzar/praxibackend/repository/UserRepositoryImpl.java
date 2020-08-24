package com.buzar.praxibackend.repository;

import com.buzar.praxibackend.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User order by username", User.class);
        List<User> users = query.getResultList();

        return users;
    }

    @Override
    public User findByUserId(int userId) {

        Session currentSession = entityManager.unwrap(Session.class);
        User tempUser = currentSession.get(User.class, userId);

        return tempUser;
    }

    @Override
    public Optional<User> findByUsername(String tempUsername) {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("from User where username=:usernameParameter");
        query.setParameter("usernameParameter", tempUsername);
        User tempUser = query.getSingleResult();

        return Optional.ofNullable(tempUser);
    }

    @Override
    public void saveOrUpdate(User tempUser) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(tempUser);
    }

    @Override
    public void deleteUser(int userId) {

        Session currentSession = entityManager.unwrap(Session.class);
        User tempUser = currentSession.get(User.class, userId);
        currentSession.delete(tempUser);
    }
}
