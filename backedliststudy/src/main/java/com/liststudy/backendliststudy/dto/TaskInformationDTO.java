package com.liststudy.backendliststudy.dto;

import java.util.List;

import com.liststudy.backendliststudy.taskrequest.UserRequestModel;

public class TaskInformationDTO {

	private boolean creator;
	private int state;
	private boolean requestAvaible;
	private boolean startProgessAvaible;
	private List<UserRequestModel> requestUsers;
	private List<UserRequestModel> acceptedUsers;
	
	public boolean isCreator() {
		return creator;
	}
	public void setCreator(boolean creator) {
		this.creator = creator;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public boolean isRequestAvaible() {
		return requestAvaible;
	}
	public void setRequestAvaible(boolean requestAvaible) {
		this.requestAvaible = requestAvaible;
	}
	public boolean isStartProgessAvaible() {
		return startProgessAvaible;
	}
	public void setStartProgessAvaible(boolean startProgessAvaible) {
		this.startProgessAvaible = startProgessAvaible;
	}
	public List<UserRequestModel> getRequestUsers() {
		return requestUsers;
	}
	public void setRequestUsers(List<UserRequestModel> requestUsers) {
		this.requestUsers = requestUsers;
	}
	public List<UserRequestModel> getAcceptedUsers() {
		return acceptedUsers;
	}
	public void setAcceptedUsers(List<UserRequestModel> acceptedUsers) {
		this.acceptedUsers = acceptedUsers;
	}

}
