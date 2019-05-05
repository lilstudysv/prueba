package com.liststudy.backendliststudy.task;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class TaskWS  {
	
	private static final Log LOG =LogFactory.getLog(TaskService.class);
		
	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;
	

	/*
	 *THIS SERVICES --> NO TOKEN
	 */
	@GetMapping("/tasks")
	public ResponseEntity<List<TaskModel>> getAllTasks() {
		//OBTIENE TODAS LAS TAREAS
		//FALTA CREAR UNO CON FILTROS
		return new ResponseEntity<List<TaskModel>>(taskService.getAllTask(), HttpStatus.OK);
	}
	
	@GetMapping("/tasks/{idTask}")
	public ResponseEntity<TaskModel> getTask(@PathVariable(value="idTask") Long idTask) {
		return new ResponseEntity<TaskModel>(taskService.getTaskModel(idTask), HttpStatus.OK);
	}

	
	
	//COMO SABEMOS LOS BOTONES
	//RECIBES --> INFORMACION A MAYORES DE LA TAREA
	//SOLICITANTES
	
	@GetMapping("/tasks/{idTask}/information")
	public ResponseEntity<TaskInformationModel> getTaskInformation(@PathVariable(value="idTask") Long idTask) {

		  LOG.info("GET /task/{idTask}/information START");
		  LOG.info("GET /task/{idTask}/information: "+idTask);
		  
		  ResponseEntity<TaskInformationModel> response = taskService.obtainInformationTask(idTask);
		  
		  LOG.info("GET /task/{idTask}/information FINISH");
		  return response;
	}

	
	
	
	
	
	
	
	
	
	//UPDATE-CREATE
	@PostMapping("/tasks")
	public ResponseEntity<TaskModel> createUpdateTasks(@RequestBody TaskModel taskModel) {
		LOG.info("/task START");
		LOG.info("/task params TaskModel: "+taskModel.toString());
		TaskModel taskModelReturn = taskService.updateCreateTask(taskModel);
		LOG.info("/task FINISH");
		return new ResponseEntity<TaskModel>(taskModelReturn, HttpStatus.OK);
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
	
	


	
}
