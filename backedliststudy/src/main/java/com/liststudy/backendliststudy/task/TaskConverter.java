package com.liststudy.backendliststudy.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.liststudy.backendliststudy.user.UserJpaRepository;

@Component("taskConverter")
public class TaskConverter {
	
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	public Task taskModelToTask(TaskModel taskModel) {
		Task task = new Task();
		
		task.setId(taskModel.getId());
		task.setState(taskModel.getState());
		task.setTopic(taskModel.getTopic());
		task.setKind(taskModel.getKind());
		task.setPrice(taskModel.getPrice());
		task.setTitle(taskModel.getTitle());
		task.setDescription(taskModel.getDescription());
		task.setCreator(userJpaRepository.findById(taskModel.getCreator()));
		task.setResolver(userJpaRepository.findById(taskModel.getResolver()));
		
		return task;
	}
	
	public TaskModel taskToTaskModel(Task task) {
		TaskModel taskModel = new TaskModel();
		
		taskModel.setId(task.getId());
		taskModel.setState(task.getState());
		taskModel.setTopic(task.getTopic());
		taskModel.setKind(task.getKind());
		taskModel.setPrice(task.getPrice());
		taskModel.setTitle(task.getTitle());
		taskModel.setDescription(task.getDescription());
		taskModel.setCreator(task.getCreator().getId());
		if(task.getResolver()!=null) {
			taskModel.setResolver(task.getResolver().getId());
		}
			
		return taskModel;
	}
	
	
}
