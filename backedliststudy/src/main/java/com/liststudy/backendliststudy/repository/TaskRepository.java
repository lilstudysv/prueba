package com.liststudy.backendliststudy.repository;

import com.liststudy.backendliststudy.dto.FiltersTaskDTO;

import java.util.List;

public interface TaskRepository {

    List getTasksFilters(FiltersTaskDTO filtersTaskDTO);

}
