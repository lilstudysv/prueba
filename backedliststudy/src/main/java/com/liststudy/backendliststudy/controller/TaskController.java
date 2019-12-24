package com.liststudy.backendliststudy.controller;

import java.util.List;

import com.liststudy.backendliststudy.dto.FiltersTaskDTO;
import com.liststudy.backendliststudy.dto.input.TaskCreateInputDTO;
import com.liststudy.backendliststudy.dto.input.TaskDeleteInputDTO;
import com.liststudy.backendliststudy.dto.input.TaskUpdateInputDTO;
import com.liststudy.backendliststudy.dto.output.TaskOutputDTO;
import com.liststudy.backendliststudy.model.Task;
import com.liststudy.backendliststudy.validator.TaskValidator;
import com.liststudy.backendliststudy.service.TaskService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tasks")
public class TaskController {

	private static final Log LOG =LogFactory.getLog(TaskService.class);
	private final TaskService taskService;
	private final TaskValidator taskValidator;

	public TaskController(@Qualifier("taskService") TaskService taskService,
						  @Qualifier("taskValidator") TaskValidator taskValidator) {
		this.taskService = taskService;
		this.taskValidator = taskValidator;
	}

	@GetMapping
	public ResponseEntity<List<TaskOutputDTO>> getAll(FiltersTaskDTO filtersTaskDTO) {
		LOG.info("GET:/tasks params filtersTaskDTO: "+ filtersTaskDTO.toString());
		return new ResponseEntity<>(taskService.getAllTask(filtersTaskDTO), HttpStatus.OK);
	}

	@GetMapping("/{idTask}")
	public ResponseEntity<TaskOutputDTO> get(@PathVariable(value="idTask") Long idTask) {
		LOG.info("GET:/tasks/{idTask] params idTask: "+ idTask.toString());
		return new ResponseEntity<>(taskService.getTaskOutputDTO(idTask), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<TaskOutputDTO> create(@RequestBody TaskCreateInputDTO taskCreateInputDTO) {
		LOG.info("POST:/tasks params TaskCreateInputDTO: "+ taskCreateInputDTO.toString());
		return ResponseEntity.ok(taskService.create(taskCreateInputDTO));
	}

	@PutMapping
	public ResponseEntity<TaskOutputDTO> update(@RequestBody TaskUpdateInputDTO taskUpdateInputDTO) {
		LOG.info("PUT:/tasks params TaskCreateInputDTO: "+ taskUpdateInputDTO.toString());

		Task task = taskService.getTask(taskUpdateInputDTO.getId());
		if(!taskValidator.isTaskValid(task)) {
			LOG.info("PUT:/tasks BAD REQUEST --> FINISH ");
			return ResponseEntity.badRequest().build();
		}

		LOG.info("PUT:/tasks FINISH");
		return ResponseEntity.ok(taskService.update(task, taskUpdateInputDTO));
	}

	@DeleteMapping("/{idTask}")
	public ResponseEntity<TaskCreateInputDTO> delete(@PathVariable(value="idTask") Long idTask) {
		LOG.info("DELETE:/tasks params idTask: "+ idTask);

		Task task = taskService.getTask(idTask);
		if(!taskValidator.isTaskValid(task)) {
			LOG.info("DELETE:/tasks BAD REQUEST --> FINISH");
            return ResponseEntity.badRequest().build();
		}

		taskService.delete(task);
		LOG.info("DELETE:/tasks FINISH");
		return ResponseEntity.ok().build();
	}

}
