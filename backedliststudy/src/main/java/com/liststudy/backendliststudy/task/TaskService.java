package com.liststudy.backendliststudy.task;

import java.util.ArrayList;
import java.util.List;

import com.liststudy.backendliststudy.security.UserLoggedToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("taskService")
public class TaskService  {
	
	private static final Log LOG =LogFactory.getLog(TaskService.class);

	private final TaskJpaRepository taskJpaRepository;
	private final TaskConverter taskConverter;
	private final UserLoggedToken userLoggedToken;

	@Autowired
	public TaskService(@Qualifier("taskJpaRepository") TaskJpaRepository taskJpaRepository,
					   @Qualifier("taskConverter") TaskConverter taskConverter,
					   @Qualifier("userLoggedToken") UserLoggedToken userLoggedToken) {

		this.taskJpaRepository = taskJpaRepository;
		this.taskConverter = taskConverter;
		this.userLoggedToken = userLoggedToken;
	}

	public List<TaskDTO> getAllTask(FiltersTaskDTO filtersTaskDTO) {
		List<Task> tasks = taskJpaRepository.getTasksFilters(filtersTaskDTO);
		List<TaskDTO> taskDTOList = new ArrayList<>();
		tasks.forEach(task -> taskDTOList.add(taskConverter.taskToTaskModel(task)));
		return taskDTOList;
	}

	public TaskDTO getTaskModel(Long id) {
		return taskConverter.taskToTaskModel(taskJpaRepository.findById(id));
	}

	public Task getTask(Long id) {
		return taskJpaRepository.findById(id);
	}

	//¿THIS METHOD HERE?
	public Boolean isTaskValid(Task task){
		return task!=null && task.getCreator().getId().equals(userLoggedToken.getUserLogged().getId());
	}

	public TaskDTO create(TaskDTO taskDTO) {
		taskDTO.setState(EnumStateTask.REQUESTED);
		taskDTO.setCreator(userLoggedToken.getUserLogged().getId());
		Task task = taskConverter.taskModelToTask(taskDTO);
		taskJpaRepository.save(task);
		return taskConverter.taskToTaskModel(task);
	}

	public TaskDTO update(Task task, TaskDTO taskDTO) {
		task = taskConverter.taskModelToTask(taskDTO, task);
		taskJpaRepository.save(task);
		return taskConverter.taskToTaskModel(task);
	}

	//TODO:TESTING VERIFY????????
	public void delete(Task task) {
		taskJpaRepository.delete(task);
	}

}
