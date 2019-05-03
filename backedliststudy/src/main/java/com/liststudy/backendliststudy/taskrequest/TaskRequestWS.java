package com.liststudy.backendliststudy.taskrequest;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liststudy.backendliststudy.task.TaskService;

@RestController
public class TaskRequestWS {

	@Autowired
	@Qualifier("taskRequestService")
	private TaskRequestService taskRequestService;
	
	private static final Log LOG =LogFactory.getLog(TaskService.class);
	

	@PostMapping("/tasks/requests")
	public ResponseEntity<String> requestByTask(@RequestBody TaskRequestModel taskRequestModel) {	
	  LOG.info("POST /task/requests/ START");
	  LOG.info("POST /task/requests/"+taskRequestModel.toString());
	  
	  ResponseEntity<String> response = taskRequestService.requestUserToTask(taskRequestModel);
	  
	  LOG.info("POST /task/requests/ FINISH");
	  return response;
	}
	
	@GetMapping("/tasks/requests")
	public ResponseEntity<List<UserRequestModel>> getUsersByTask(@RequestBody TaskRequestModel taskRequestModel) {	
	  LOG.info("GET /task/requests/ START");
	  LOG.info("GET /task/requests/"+taskRequestModel.toString());
	  
	  ResponseEntity<List<UserRequestModel>> response = taskRequestService.getRequestUsersByTask(taskRequestModel);
	  
	  LOG.info("GET /task/requests/ FINISH");
	  return response;
	}
	
	
	@DeleteMapping("/tasks/requests")
	public ResponseEntity<String> deleteRequestByTask(@RequestBody TaskRequestModel taskRequestModel) {	
	  LOG.info("DELETE /task/requests/ START");
	  LOG.info("DELETE /task/requests/"+taskRequestModel.toString());
	  
	  ResponseEntity<String> response = taskRequestService.deleteRequestByTask(taskRequestModel);
	  
	  LOG.info("DELETE /task/request/ FINISH");
	  return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@PostMapping("/tasks/requests/{idTask}/{idUsuario}")
	public ResponseEntity<String> chooseRequest(@PathVariable(value="idTask") Long id,
									@PathVariable(value="idUsuario") Long idUsuario){
		// NO EXISTE LA TAREA
		// EL USUARIO NO EXISTE O ERES TU EL QUE ESTA ASIGNADA A LA TAREA
		
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	
	@PostMapping("/tasks/resolve/{idTask}")
	public ResponseEntity<String> resolve(@PathVariable(value="idTask") Long id){
		// NO EXISTE LA TAREA
		// EL USUARIO NO ERES TU
		
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	
	
	@PostMapping("/tasks/inProgess/{idTask}")
	public ResponseEntity<String> startResolve(@PathVariable(value="idTask") Long id){
		// NO EXISTE LA TAREA
		// EL USUARIO NO ERES TU
		
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	
	@PostMapping("/tasks/finalice/{idTask}")
	public ResponseEntity<String> finalice(@PathVariable(value="idTask") Long id){
		// NO EXISTE LA TAREA
		// EL USUARIO NO EXISTE O ERES TU
		
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
*/
	
}
