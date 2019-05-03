package com.liststudy.backendliststudy.taskrequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.liststudy.backendliststudy.task.EnumStateTask;
import com.liststudy.backendliststudy.task.Task;
import com.liststudy.backendliststudy.task.TaskJpaRepository;
import com.liststudy.backendliststudy.user.User;
import com.liststudy.backendliststudy.user.UserJpaRepository;

@Service("taskRequestService")
public class TaskRequestService {

	//To diference if is a request task or accepted task
	public static final int REQUEST_TASK=1;
	public static final int ACCEPTED_TASK=2;
	
	
	@Autowired
	@Qualifier("taskJpaRepository")
	private TaskJpaRepository taskJpaRepository;

	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	@Autowired
	@Qualifier("userRequestConverter")
	private UserRequestConverter userRequestConverter;
	
	
	public ResponseEntity<String> requestUserToTask(TaskRequestModel taskRequestModel) {
		String login = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userLogueado = userJpaRepository.findByUsername(login);
		
		//NO CUMPLE LOS REQUESITOS EL USUARIO
		
		//NO CUMPLE LOS REQUISITOS LA TAREA
		Task task = taskJpaRepository.findById(taskRequestModel.getIdTask());
		if(task==null||!task.getState().equals(EnumStateTask.REQUESTED)) {
			return new ResponseEntity<String>("",HttpStatus.NOT_ACCEPTABLE);
		}
				
		if(taskRequestModel.getKindSubmit() == REQUEST_TASK && !task.getRequestUsers().contains(userLogueado)) {
    		task.getRequestUsers().add(userLogueado);
	    	taskJpaRepository.save(task);
	    	return new ResponseEntity<String>("",HttpStatus.OK);
    	}else if(taskRequestModel.getKindSubmit()  == ACCEPTED_TASK && !task.getAcceptedUsers().contains(userLogueado)) {
    		task.getAcceptedUsers().add(userLogueado);
	    	taskJpaRepository.save(task);
	    	return new ResponseEntity<String>("",HttpStatus.OK);
    	}else {
    		return new ResponseEntity<String>("",HttpStatus.NOT_ACCEPTABLE);
    	}
	
	}

    
    
    public ResponseEntity<List<UserRequestModel>> getRequestUsersByTask(TaskRequestModel taskRequestModel){
    	Task task = taskJpaRepository.findById(taskRequestModel.getIdTask());
		if(task==null||!task.getState().equals(EnumStateTask.REQUESTED)) {
			return new ResponseEntity<List<UserRequestModel>>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		List<UserRequestModel> requestUsers = new ArrayList<>();	
		for (User user: obtainRequestAcceptedUsers(taskRequestModel.getKindSubmit() , task)) {
			requestUsers.add(userRequestConverter.userToUserModel(user));
		}
		
		return new ResponseEntity<List<UserRequestModel>>(requestUsers, HttpStatus.OK);
		
    }

	private List<User> obtainRequestAcceptedUsers(int REQUEST_ACCEPTED_TASK, Task task) {
		return REQUEST_ACCEPTED_TASK == REQUEST_TASK ? task.getRequestUsers() : task.getAcceptedUsers(); 
	}



	public ResponseEntity<String> deleteRequestByTask(TaskRequestModel taskRequestModel) {
		String login = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userLogueado = userJpaRepository.findByUsername(login);
		
		//NO CUMPLE LOS REQUESITOS EL USUARIO
		
		//NO CUMPLE LOS REQUISITOS LA TAREA
		Task task = taskJpaRepository.findById(taskRequestModel.getIdTask());
		if(task==null||!task.getState().equals(EnumStateTask.REQUESTED)) {
			return new ResponseEntity<String>("",HttpStatus.NOT_ACCEPTABLE);
		}
		
	  	if(taskRequestModel.getKindSubmit()  == REQUEST_TASK && task.getRequestUsers().contains(userLogueado)) {
    		task.getRequestUsers().remove(userLogueado);
	    	taskJpaRepository.save(task);
			return new ResponseEntity<String>("",HttpStatus.OK);
	  	}else if(taskRequestModel.getKindSubmit()  == ACCEPTED_TASK && task.getAcceptedUsers().contains(userLogueado)) {
			task.getAcceptedUsers().remove(userLogueado);
	    	taskJpaRepository.save(task);
			return new ResponseEntity<String>("",HttpStatus.OK);
	  	}else {
	  		return new ResponseEntity<String>("",HttpStatus.NOT_ACCEPTABLE);
	  	}
	}
	

}
