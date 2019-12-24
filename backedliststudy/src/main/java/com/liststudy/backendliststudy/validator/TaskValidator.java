package com.liststudy.backendliststudy.validator;

import com.liststudy.backendliststudy.model.Task;
import com.liststudy.backendliststudy.security.UserLoggedToken;
import org.springframework.stereotype.Service;

@Service("taskValidator")
public class TaskValidator {

	private UserLoggedToken userLoggedToken;

	public TaskValidator(UserLoggedToken userLoggedToken) {
		this.userLoggedToken = userLoggedToken;
	}

	public Boolean isTaskValid(Task task){
		return task!=null && task.getCreator().getId().equals(userLoggedToken.getUserLogged().getId());
	}

}
