package com.liststudy.backendliststudy.task;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liststudy.backendliststudy.user.User;

@Repository("taskJpaRepository")
public interface TaskJpaRepository extends JpaRepository<Task, Serializable>{

	public abstract Task findById(Long username);
}
