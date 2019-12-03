package com.liststudy.backendliststudy.controller;

import java.util.List;

import com.liststudy.backendliststudy.dto.FiltersTaskDTO;
import com.liststudy.backendliststudy.dto.TaskDTO;
import com.liststudy.backendliststudy.model.Task;
import com.liststudy.backendliststudy.service.TaskInputParamsValidator;
import com.liststudy.backendliststudy.service.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tasks")
public class TaskController {

	private static final Log LOG =LogFactory.getLog(TaskService.class);
	private final TaskService taskService;
	private final TaskInputParamsValidator taskInputParamsValidator;

	@Autowired
	public TaskController(@Qualifier("taskService") TaskService taskService,
						  @Qualifier("taskInputParamsValidator") TaskInputParamsValidator taskInputParamsValidator) {
		this.taskService = taskService;
		this.taskInputParamsValidator = taskInputParamsValidator;
	}

	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAll(FiltersTaskDTO filtersTaskDTO) {
		LOG.info("GET:/tasks params filtersTaskDTO: "+ filtersTaskDTO.toString());
		return new ResponseEntity<>(taskService.getAllTask(filtersTaskDTO), HttpStatus.OK);
	}

	@GetMapping("/{idTask}")
	public ResponseEntity<TaskDTO> get(@PathVariable(value="idTask") Long idTask) {
		LOG.info("GET:/tasks/{idTask] params idTask: "+ idTask.toString());
		return new ResponseEntity<>(taskService.getTaskModel(idTask), HttpStatus.OK);
	}

	@PostMapping
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

	@PutMapping
	public ResponseEntity<TaskDTO> update(@RequestBody TaskDTO taskDTO) {
		LOG.info("PUT:/tasks params TaskDTO: "+ taskDTO.toString());

		if(!taskInputParamsValidator.validateUpdateRigth(taskDTO)) {
			LOG.info("PUT:/tasks NOT ACCEPTABLE --> FINISH");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		Task task = taskService.getTask(taskDTO.getId());
		if(!taskService.isTaskValid(task)) {
			LOG.info("PUT:/tasks NOT ACCEPTABLE --> FINISH 2");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		TaskDTO taskDTOReturn = taskService.update(task, taskDTO);
		LOG.info("PUT:/tasks FINISH");
		return new ResponseEntity<>(taskDTOReturn, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<TaskDTO> delete(@RequestBody TaskDTO taskDTO) {
		LOG.info("DELETE:/tasks params TaskDTO: "+ taskDTO.toString());

		if(!taskInputParamsValidator.validateDeleteRigth(taskDTO)) {
			LOG.info("DELETE:/tasks NOT ACCEPTABLE --> FINISH");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		Task task = taskService.getTask(taskDTO.getId());
		if(!taskService.isTaskValid(task)) {
			LOG.info("DELETE:/tasks NOT ACCEPTABLE --> FINISH 2");
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

		taskService.delete(task);
		LOG.info("DELETE:/tasks FINISH");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
