package com.liststudy.backendliststudy.repository;

import com.liststudy.backendliststudy.dto.FiltersTaskDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaskRepositoryTest {

    private TaskRepositoryImpl taskRepository = new TaskRepositoryImpl();

    @Test
    public void generateQuery_noFilters(){
        FiltersTaskDTO filtersTaskDTO = new FiltersTaskDTO();
        String query = "SELECT t.* FROM task t WHERE t.state = 0";

        Assert.assertEquals(query, taskRepository.generateQuery(filtersTaskDTO));
    }

    @Test
    public void generateQuery_filterTopic(){
        String query = "SELECT t.* FROM task t WHERE t.state = 0 AND t.topic = 1";
        FiltersTaskDTO filtersTaskDTO = new FiltersTaskDTO();
        filtersTaskDTO.setIdTopic(1);

        Assert.assertEquals(query, taskRepository.generateQuery(filtersTaskDTO));
    }

    @Test
    public void generateQuery_filterMinPrice(){
        FiltersTaskDTO filtersTaskDTO = new FiltersTaskDTO();
        filtersTaskDTO.setPriceMin(10D);
        String query = "SELECT t.* FROM task t WHERE t.state = 0 AND t.price > 10.0";

        Assert.assertEquals(query, taskRepository.generateQuery(filtersTaskDTO));
    }

    @Test
    public void generateQuery_filterMaxPrice(){
        FiltersTaskDTO filtersTaskDTO = new FiltersTaskDTO();
        filtersTaskDTO.setPriceMax(30D);
        String query = "SELECT t.* FROM task t WHERE t.state = 0 AND t.price < 30.0";

        Assert.assertEquals(query, taskRepository.generateQuery(filtersTaskDTO));
    }

    @Test
    public void generateQuery_allFilters(){
        FiltersTaskDTO filtersTaskDTO = new FiltersTaskDTO();
        filtersTaskDTO.setIdTopic(2);
        filtersTaskDTO.setPriceMin(15D);
        filtersTaskDTO.setPriceMax(20D);
        String query = "SELECT t.* FROM task t WHERE t.state = 0" +
                " AND t.topic = 2 AND t.price > 15.0 AND t.price < 20.0";

        Assert.assertEquals(query, taskRepository.generateQuery(filtersTaskDTO));
    }
}
