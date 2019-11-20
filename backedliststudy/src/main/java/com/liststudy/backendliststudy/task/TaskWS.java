package com.liststudy.backendliststudy.task;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaskWS  {

	private static final Log LOG =LogFactory.getLog(TaskService.class);
	private final TaskService taskService;
	private final TaskInputParamsValidator taskInputParamsValidator;

	@Autowired
	public TaskWS(@Qualifier("taskService") TaskService taskService,
				  @Qualifier("taskInputParamsValidator") TaskInputParamsValidator taskInputParamsValidator) {
		this.taskService = taskService;
		this.taskInputParamsValidator = taskInputParamsValidator;
	}

	@GetMapping("/tasks")
	public ResponseEntity<List<TaskDTO>> getAll(FiltersTaskDTO filtersTaskDTO) {
		//TODO: NEED ADD FILTERS
		return new ResponseEntity<>(taskService.getAllTask(filtersTaskDTO), HttpStatus.OK);
	}

	@GetMapping("/tasks/{idTask}")
	public ResponseEntity<TaskDTO> get(@PathVariable(value="idTask") Long idTask) {
		return new ResponseEntity<>(taskService.getTaskModel(idTask), HttpStatus.OK);
	}

	@PostMapping("/tasks")
	public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO) {
		LOG.info("POST:/tasks params TaskDTO: "+ taskDTO.toString());

		if(!taskInputParamsValidator.validateCreateRigth(taskDTO)) {
			LOG.info("POST:/tasks NOT ACCEPTABLE --> FINISH");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		TaskDTO taskDTOReturn = taskService.create(taskDTO);
		LOG.info("POST:/tasks FINISH");
		return new ResponseEntity<>(taskDTOReturn, HttpStatus.OK);
	}

	@PutMapping("/tasks")
	public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO taskDTO) {
		LOG.info("PUT:/tasks params TaskDTO: "+ taskDTO.toString());

		if(!taskInputParamsValidator.validateUpdateRigth(taskDTO)) {
			LOG.info("PUT:/tasks NOT ACCEPTABLE --> FINISH");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		Task task = taskService.getTask(taskDTO.getId());
		//TODO: CREATOR NULL --> ERROR --> @ControllerAdvice  
		if(!taskService.isTaskValid(task)) {
			LOG.info("PUT:/tasks NOT ACCEPTABLE --> FINISH 2");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		TaskDTO taskDTOReturn = taskService.update(task, taskDTO);
		LOG.info("PUT:/tasks FINISH");
		return new ResponseEntity<>(taskDTOReturn, HttpStatus.OK);
	}

	@DeleteMapping("/tasks")
	public ResponseEntity<TaskDTO> delete(@RequestBody TaskDTO taskDTO) {
		LOG.info("DELETE:/tasks params TaskDTO: "+ taskDTO.toString());

		if(!taskInputParamsValidator.validateDeleteRigth(taskDTO)) {
			LOG.info("DELETE:/tasks NOT ACCEPTABLE --> FINISH");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		Task task = taskService.getTask(taskDTO.getId());
		//TODO: CREATOR NULL --> ERROR --> @ControllerAdvice  
		if(!taskService.isTaskValid(task)) {
			LOG.info("DELETE:/tasks NOT ACCEPTABLE --> FINISH 2");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		taskService.delete(task);
		LOG.info("DELETE:/tasks FINISH");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
