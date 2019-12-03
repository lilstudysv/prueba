package com.liststudy.backendliststudy.service;

import com.liststudy.backendliststudy.dto.TaskDTO;
import com.liststudy.backendliststudy.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.liststudy.backendliststudy.user.UserJpaRepository;

@Component("taskConverter")
public class TaskConverter {
	
	private final UserJpaRepository userJpaRepository;

	@Autowired
	public TaskConverter(@Qualifier("userJpaRepository") UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	public Task taskModelToTask(TaskDTO taskDTO) {
		Task task = new Task();
		
		task.setId(taskDTO.getId());
		task.setState(taskDTO.getState());
		task.setTopic(taskDTO.getTopic());
		task.setKind(taskDTO.getKind());
		task.setPrice(taskDTO.getPrice());
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		task.setCreator(userJpaRepository.findById(taskDTO.getCreator()));
		
		return task;
	}
	
	
	public Task taskModelToTask(TaskDTO taskDTO, Task task) {
	
		task.setTopic(taskDTO.getTopic());
		task.setKind(taskDTO.getKind());
		task.setPrice(taskDTO.getPrice());
		task.setTitle(taskDTO.getTitle());
		task.setDescription(taskDTO.getDescription());
		
		return task;
	}
	
	public TaskDTO taskToTaskModel(Task task) {
		TaskDTO taskDTO = new TaskDTO();
		
		taskDTO.setId(task.getId());
		taskDTO.setState(task.getState());
		taskDTO.setTopic(task.getTopic());
		taskDTO.setKind(task.getKind());
		taskDTO.setPrice(task.getPrice());
		taskDTO.setTitle(task.getTitle());
		taskDTO.setDescription(task.getDescription());
		taskDTO.setCreator(task.getCreator().getId());

			
		return taskDTO;
	}
	
	
}
