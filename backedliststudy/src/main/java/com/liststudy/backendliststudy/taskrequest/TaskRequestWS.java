package com.liststudy.backendliststudy.taskrequest;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liststudy.backendliststudy.task.TaskService;

@RestController
public class TaskRequestWS {

	private static final Log LOG =LogFactory.getLog(TaskService.class);

	@Autowired
	@Qualifier("taskRequestService")
	private TaskRequestService taskRequestService;
	

	@PatchMapping("/tasks/requests")
	public ResponseEntity<String> requestTask(@RequestBody TaskRequestModel taskRequestModel) {	
	  LOG.info("POST /task/requests/ START");
	  LOG.info("POST /task/requests/"+taskRequestModel.toStringIdTask());
	  
	  ResponseEntity<String> response = taskRequestService.taskRequestAll(taskRequestModel, TaskRequestService.REQUEST_TASK);
	  
	  LOG.info("POST /task/requests/ FINISH");
	  return response;
	}
	
	@DeleteMapping("/tasks/requests")
	public ResponseEntity<String> deleteRequestTask(@RequestBody TaskRequestModel taskRequestModel) {	
	  LOG.info("DELETE /task/requests/ START");
	  LOG.info("DELETE /task/requests/"+taskRequestModel.toStringIdTask());
	  
	  ResponseEntity<String> response = taskRequestService.taskRequestAll(taskRequestModel, TaskRequestService.DELETE_REQUEST_TASK);
	  
	  LOG.info("DELETE /task/request/ FINISH");
	  return response;
	}
	
	
	@PatchMapping("/tasks/accepteds")
	public ResponseEntity<String> acceptUser(@RequestBody TaskRequestModel taskRequestModel) {	
	  LOG.info("POST /task/accepteds/ START");
	  LOG.info("POST /task/accepteds/"+taskRequestModel.toStringAll());
	  
	  ResponseEntity<String> response = taskRequestService.taskRequestAll(taskRequestModel, TaskRequestService.ACCEPTED_USER);
	  
	  LOG.info("POST /task/accepteds/ FINISH");
	  return response;
	}
	
	@DeleteMapping("/tasks/accepteds")
	public ResponseEntity<String> deleteAcceptedUser(@RequestBody TaskRequestModel taskRequestModel) {	
	  LOG.info("DELETE /task/accepteds/ START");
	  LOG.info("DELETE /task/accepteds/"+taskRequestModel.toStringAll());
	  
	  ResponseEntity<String> response = taskRequestService.taskRequestAll(taskRequestModel, TaskRequestService.DELETE_ACCEPTED_USER);
	  
	  LOG.info("DELETE /task/accepteds/ FINISH");
	  return response;
	}
	
}
