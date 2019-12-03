package com.liststudy.backendliststudy.repository;

import com.liststudy.backendliststudy.dto.FiltersTaskDTO;
import com.liststudy.backendliststudy.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static String QUERY_TASKS= "SELECT t.*" +
            " FROM task t" +
            " WHERE t.state = 0"; //0 is REQUESTED_TASKS
    private static String TOPIC_FILTER = " AND t.topic = :topic";
    private static String PRICE_MIN_FILTER = " AND t.price > :minPrice";
    private static String PRICE_MAX_FILTER = " AND t.price < :maxPrice";

    @Override
    public List<Task> getTasksFilters(FiltersTaskDTO filtersTaskDTO) {
        return entityManager.createNativeQuery(generateQuery(filtersTaskDTO), Task.class).getResultList();
    }

    public String generateQuery(FiltersTaskDTO filtersTaskDTO){
        String query = QUERY_TASKS;

        if(filtersTaskDTO.getIdTopic()!=null){
            query += TOPIC_FILTER.replace(":topic",filtersTaskDTO.getIdTopic().toString());
        }
        if(filtersTaskDTO.getPriceMin()!=null){
            query += PRICE_MIN_FILTER.replace(":minPrice",filtersTaskDTO.getPriceMin().toString());
        }
        if(filtersTaskDTO.getPriceMax()!=null){
            query += PRICE_MAX_FILTER.replace(":maxPrice",filtersTaskDTO.getPriceMax().toString());
        }

        return query;
    }
}
