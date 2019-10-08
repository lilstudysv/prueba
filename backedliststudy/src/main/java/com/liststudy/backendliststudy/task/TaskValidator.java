package com.liststudy.backendliststudy.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.liststudy.backendliststudy.user.User;
import com.liststudy.backendliststudy.user.UserJpaRepository;

@Service("taskValidator")
public class TaskValidator {
	
	@Autowired
	@Qualifier("userJpaRepository")
	private UserJpaRepository userJpaRepository;
	
	public boolean validateCreateRigth(TaskModel taskModel) {
		return  taskModel.getTopic() != null && taskModel.getKind() != null && 
				taskModel.getTitle() != null && taskModel.getDescription() != null 
				&& taskModel.getPrice() != null ;
	}
	
	public boolean validateUpdateRigth(TaskModel taskModel) {
		return validateCreateRigth(taskModel) && taskModel.getId() != null ;
	}
	
	public boolean validateDeleteRigth(TaskModel taskModel) {
		return taskModel.getId() != null ;
	}

	public boolean validateUserTask(Task task) {
		/*TODO: HERENCIA SE VA A USAR EN MUCHoS SITIOS */
		String login = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userLogueado = userJpaRepository.findByUsername(login);
		return userLogueado.getId().equals(task.getCreator().getId());
	}
}
