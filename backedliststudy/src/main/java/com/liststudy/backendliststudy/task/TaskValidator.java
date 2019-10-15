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
		return validatePrice(taskModel.getPrice()) && validateTitle(taskModel.getTitle())
				&& validateDescription(taskModel.getDescription());
	}
	
	public boolean validateDeleteRigth(TaskModel taskModel) {
		return taskModel.getId() != null && validatePrice(taskModel.getPrice()) 
				&& validateTitle(taskModel.getTitle()) && validateDescription(taskModel.getDescription());
	}
	
	public boolean validateUpdateRigth(TaskModel taskModel) {
		return validateCreateRigth(taskModel) && taskModel.getId() != null ;
	}
	
	private boolean validatePrice(Double price) {
		return price!=null && price>0;
	}
	
	private boolean validateTitle(String title) {
		return title!=null && !"".equals(title);
	}
	
	private boolean validateDescription(String description) {
		return description!=null && !"".equals(description);
	}
	
	public boolean validateUserTask(Task task) {
		/*TODO: Study this*/
		String login = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userLogueado = userJpaRepository.findByUsername(login);
		return userLogueado.getId().equals(task.getCreator().getId());
	}
}
