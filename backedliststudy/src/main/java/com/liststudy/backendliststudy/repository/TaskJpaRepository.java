package com.liststudy.backendliststudy.repository;

import java.io.Serializable;

import com.liststudy.backendliststudy.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("taskJpaRepository")
public interface TaskJpaRepository extends JpaRepository<Task, Serializable>, TaskRepository{

	Task findById(Long id);
}
