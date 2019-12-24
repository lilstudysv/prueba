package com.liststudy.backendliststudy.service;

import java.util.List;

import com.liststudy.backendliststudy.dto.FiltersTaskDTO;
import com.liststudy.backendliststudy.dto.input.TaskCreateInputDTO;
import com.liststudy.backendliststudy.dto.input.TaskUpdateInputDTO;
import com.liststudy.backendliststudy.dto.output.TaskOutputDTO;
import com.liststudy.backendliststudy.mapper.TaskMapper;
import com.liststudy.backendliststudy.model.EnumStateTask;
import com.liststudy.backendliststudy.model.Task;
import com.liststudy.backendliststudy.security.UserLoggedToken;
import com.liststudy.backendliststudy.repository.TaskJpaRepository;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("taskService")
public class TaskService  {
	

	private final TaskJpaRepository taskJpaRepository;
	private final UserLoggedToken userLoggedToken;
	private final TaskMapper taskMapper = Mappers.getMapper( TaskMapper.class );


	@Autowired
	public TaskService(@Qualifier("taskJpaRepository") TaskJpaRepository taskJpaRepository,
					   @Qualifier("userLoggedToken") UserLoggedToken userLoggedToken) {
		this.taskJpaRepository = taskJpaRepository;
		this.userLoggedToken = userLoggedToken;
	}

	public List<TaskOutputDTO> getAllTask(FiltersTaskDTO filtersTaskDTO) {
		return taskMapper.taskToTaskOutputDTO(taskJpaRepository.getTasksFilters(filtersTaskDTO));
	}

	public TaskOutputDTO getTaskOutputDTO(Long id) {
		return taskMapper.taskToTaskOutputDTO(taskJpaRepository.findById(id));
	}

	public Task getTask(Long id) {
		return taskJpaRepository.findById(id);
	}


	public TaskOutputDTO create(TaskCreateInputDTO taskCreateInputDTO) {
		Task task = taskMapper.taskCreateImputDTOtoTask(taskCreateInputDTO);
		task.setState(EnumStateTask.REQUESTED);
		task.setCreator(userLoggedToken.getUserLogged());
		taskJpaRepository.save(task);
		return taskMapper.taskToTaskOutputDTO(task);
	}

	public TaskOutputDTO update(Task task, TaskUpdateInputDTO taskUpdateInputDTO) {
		task = taskMapper.taskUpdateImputDTOtoTask(taskUpdateInputDTO, task);
		taskJpaRepository.save(task);
		return taskMapper.taskToTaskOutputDTO(task);
	}

	public void delete(Task task) {
		taskJpaRepository.delete(task);
	}

}
