package com.liststudy.backendliststudy.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskWSImpl  {
	@Autowired
	@Qualifier("taskServiceImpl")
	private TaskServiceImpl taskServiceImpl;
	
	@GetMapping("/tasks")
	public ResponseEntity<List<TaskModel>> getAllTasks() {
		return new ResponseEntity<List<TaskModel>>(taskServiceImpl.getAllTask(), HttpStatus.OK);
	}
	
	@GetMapping("/tasks/{id}")
	public ResponseEntity<TaskModel> getTask(@PathVariable(value="id") Long id) {
		return new ResponseEntity<TaskModel>(taskServiceImpl.getTask(id), HttpStatus.OK);
	}

	@PostMapping("/tasks")
	public void updateCreateTasks(@RequestBody TaskModel taskModel) {
		taskServiceImpl.updateCreateTask(taskModel);
	}
	
	@PostMapping("/tasks/assigneds/{id}")
	public ResponseEntity<String> updateCreateTasks(@PathVariable(value="id") Long id) {
		if(taskServiceImpl.assignTask(id)) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("", HttpStatus.LOCKED);
		}
	}

}
