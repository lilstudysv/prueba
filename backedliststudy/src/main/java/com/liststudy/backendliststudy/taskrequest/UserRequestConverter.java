package com.liststudy.backendliststudy.taskrequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.liststudy.backendliststudy.user.User;

@Component("userRequestConverter")
public class UserRequestConverter {

	public UserRequestModel userToUserModel(User user) {
		UserRequestModel userModel = new UserRequestModel();
	
		userModel.setId(user.getId());
		userModel.setAverageGrade(23l);
		userModel.setTotalTaskResolved(22);
		userModel.setName(user.getUsername());
		
		return userModel;
	}


	public List<UserRequestModel> listUsersToListUserRequest(List<User> users) {
		List<UserRequestModel> usersRequest = new ArrayList<>();
		
		for(User user: users) {
			usersRequest.add(userToUserModel(user));
		}
		
		return usersRequest;
	}
	
}