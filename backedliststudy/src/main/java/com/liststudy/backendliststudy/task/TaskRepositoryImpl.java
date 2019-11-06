package com.liststudy.backendliststudy.task;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static String QUERY_OBTAIN_TASKS = "select * from task";

    @Override
    public List<Task> getTasksFilters() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(QUERY_OBTAIN_TASKS);
        return query.getResultList();
    }
}
