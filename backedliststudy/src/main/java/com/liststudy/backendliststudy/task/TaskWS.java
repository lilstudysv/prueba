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
		
	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;
	
	@Autowired
	@Qualifier("taskValidator")
	private TaskValidator taskValidator;

	@GetMapping("/tasks")
	public ResponseEntity<List<TaskModel>> getAll() {
		//OBTIENE TODAS LAS TAREAS
		//FALTA CREAR UNO CON FILTROS
		return new ResponseEntity<List<TaskModel>>(taskService.getAllTask(), HttpStatus.OK);
	}
	
	@GetMapping("/tasks/{idTask}")
	public ResponseEntity<TaskModel> get(@PathVariable(value="idTask") Long idTask) {
		return new ResponseEntity<TaskModel>(taskService.getTaskModel(idTask), HttpStatus.OK);
	}
	
	
	@PostMapping("/tasks")
	public ResponseEntity<TaskModel> create(@RequestBody TaskModel taskModel) {
		LOG.info("POST:/tasks params TaskModel: "+taskModel.toString());

		if(!taskValidator.validateCreateRigth(taskModel)) {
			LOG.info("POST:/tasks NOT ACCEPTABLE --> FINISH");
			return new ResponseEntity<TaskModel>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		TaskModel taskModelReturn = taskService.create(taskModel);
		LOG.info("POST:/tasks FINISH");
		return new ResponseEntity<TaskModel>(taskModelReturn, HttpStatus.OK);
	}
	
	
	@PutMapping("/tasks")
	public ResponseEntity<TaskModel> update(@RequestBody TaskModel taskModel) {
		LOG.info("PUT:/tasks params TaskModel: "+taskModel.toString());
		
		if(!taskValidator.validateUpdateRigth(taskModel)) {
			LOG.info("PUT:/tasks NOT ACCEPTABLE --> FINISH");
			return new ResponseEntity<TaskModel>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		Task task = taskService.getTask(taskModel.getId());
		if(task==null || !taskValidator.validateUserTask(task)) {
			LOG.info("PUT:/tasks NOT ACCEPTABLE --> FINISH 2");
			return new ResponseEntity<TaskModel>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		TaskModel taskModelReturn = taskService.update(task, taskModel);
		LOG.info("PUT:/tasks FINISH");
		return new ResponseEntity<TaskModel>(taskModelReturn, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/tasks")
	public ResponseEntity<TaskModel> delete(@RequestBody TaskModel taskModel) {
		LOG.info("DELETE:/tasks params TaskModel: "+taskModel.toString());
		
		if(!taskValidator.validateDeleteRigth(taskModel)) {
			LOG.info("DELETE:/tasks NOT ACCEPTABLE --> FINISH");
			return new ResponseEntity<TaskModel>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		Task task = taskService.getTask(taskModel.getId());
		if(task==null || !taskValidator.validateUserTask(task)) {
			LOG.info("DELETE:/tasks NOT ACCEPTABLE --> FINISH 2");
			return new ResponseEntity<TaskModel>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		taskService.delete(task);
		LOG.info("DELETE:/tasks FINISH");
		return new ResponseEntity<TaskModel>(HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	/////////BLOQUEO OPTIMISTA
	
	
	
	//CUANDO SE NECESITE EL BLOQUEO OPTIMISTA 
	//NOS VALE DE PRUEBA
	@PostMapping("/tasks/assigneds/{id}")
	public ResponseEntity<String> updateCreateTasks(@PathVariable(value="id") Long id) {
		if(taskService.assignTask(id)) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("", HttpStatus.LOCKED);
		}
	}
	
	
	
	//COMO SABEMOS LOS BOTONES
		//RECIBES --> INFORMACION A MAYORES DE LA TAREA
		//SOLICITANTES
		
		@GetMapping("/information/tasks/{idTask}")
		public ResponseEntity<TaskInformationModel> getTaskInformation(@PathVariable(value="idTask") Long idTask) {

			  LOG.info("GET /task/{idTask}/information START");
			  LOG.info("GET /task/{idTask}/information: "+idTask);
			  
			  ResponseEntity<TaskInformationModel> response = taskService.obtainInformationTask(idTask);
			  
			  LOG.info("GET /task/{idTask}/information FINISH");
			  return response;
		}

		



	
}
