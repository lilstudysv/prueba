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
    private static String FILTER_MIN_PRICE = "";
    private static String FILTER_MAX_PRICE="";
    private static String FILTER_TOPIC_PRICE="";
    private static String FILTER_NAME="";

    @Override
    public List<Task> getTasksFilters(FiltersTaskDTO filtersTaskDTOTO) {
/*
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createSQLQuery(QUERY_OBTAIN_TASKS);
*/
        return entityManager.createQuery(QUERY_OBTAIN_TASKS, Task.class).getResultList();
    }
}
