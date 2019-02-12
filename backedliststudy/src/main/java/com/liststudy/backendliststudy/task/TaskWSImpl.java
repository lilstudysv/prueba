package com.liststudy.backendliststudy.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskWSImpl  {
	@Autowired
	@Qualifier("taskJpaRepository")
	private TaskJpaRepository taskJpaRepository;
	
	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getAllTasks() {
		return new ResponseEntity<List<Task>>(taskJpaRepository.findAll(), HttpStatus.OK);
	}
}
