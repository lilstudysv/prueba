package com.liststudy.backendliststudy.taskrequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.liststudy.backendliststudy.model.EnumStateTask;
import com.liststudy.backendliststudy.model.Task;
import com.liststudy.backendliststudy.repository.TaskJpaRepository;
import com.liststudy.backendliststudy.user.User;
import com.liststudy.backendliststudy.user.UserJpaRepository;

@Service("taskRequestService")
public class TaskRequestService {
	
	public static final int REQUEST_TASK = 1;
	public static final int DELETE_REQUEST_TASK = 2;
	public static final int ACCEPTED_USER = 3;
	public static final int DELETE_ACCEPTED_USER = 4;
	
	@Autowired
	@Qualifier("taskJpaRepository")
	private TaskJpaRepository taskJpaRepository;

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	@Qualifier("userRequestConverter")
	private UserRequestConverter userRequestConverter;
	
	public ResponseEntity<String> taskRequestAll(TaskRequestModel taskRequestModel, int kindSubmit){
		String login = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userLogueado = userJpaRepository.findByUsername(login);	
		//NO CUMPLE LOS REQUESITOS EL USUARIO
		
		
		//NO CUMPLE LOS REQUISITOS LA TAREA
		Task task = taskJpaRepository.findById(taskRequestModel.getIdTask());
		if(task==null||!task.getState().equals(EnumStateTask.REQUESTED)) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		
		if(kindSubmit==REQUEST_TASK) {
			return requestTask(userLogueado, task);
		}else if(kindSubmit==DELETE_REQUEST_TASK) {
			return deleteRequestTask(userLogueado, task);
		}else if(kindSubmit==ACCEPTED_USER) {
			User userAccepted = userJpaRepository.findById(taskRequestModel.getIdUserDeleteAccepted());
			return acceptUser(task, userAccepted);
		}else if(kindSubmit==DELETE_ACCEPTED_USER) {
			User userAccepted = userJpaRepository.findById(taskRequestModel.getIdUserDeleteAccepted());
			return deleteAcceptedUser(task, userAccepted);
		}else {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	
	
	public ResponseEntity<String> requestTask(User userLogueado, Task task) {
		
		if( !task.getRequestUsers().contains(userLogueado)) {
    		task.getRequestUsers().add(userLogueado);
	    	taskJpaRepository.save(task);
	    	return new ResponseEntity<String>(HttpStatus.OK);
    	}else {
    		return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
    	}
	
	}


	public ResponseEntity<String> deleteRequestTask(User userLogueado, Task task) {

		
	  	if( task.getRequestUsers().contains(userLogueado)) {
    		task.getRequestUsers().remove(userLogueado);
	    	taskJpaRepository.save(task);
			return new ResponseEntity<String>(HttpStatus.OK);
	  	}else {
	  		return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
	  	}
	}
	
	
	
	public ResponseEntity<String> acceptUser( Task task, User userAccepted) {
		if(  task.getRequestUsers().contains(userAccepted) && !task.getAcceptedUsers().contains(userAccepted) ) {
    		task.getAcceptedUsers().add(userAccepted);
    		task.getRequestUsers().remove(userAccepted);
	    	taskJpaRepository.save(task);
	    	return new ResponseEntity<String>(HttpStatus.OK);
    	}else {
    		return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
    	}
	}

	public ResponseEntity<String> deleteAcceptedUser( Task task, User userAccepted) {
	  	if( task.getAcceptedUsers().contains(userAccepted)) {
    		task.getAcceptedUsers().remove(userAccepted);
    		task.getRequestUsers().add(userAccepted);
	    	taskJpaRepository.save(task);
			return new ResponseEntity<String>(HttpStatus.OK);
	  	}else {
	  		return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
	  	}
	}

	
}
