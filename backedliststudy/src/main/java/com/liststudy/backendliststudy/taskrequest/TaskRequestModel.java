package com.liststudy.backendliststudy.taskrequest;

public class TaskRequestModel {

	private Long idTask;
	private int kindSubmit;
	

	public String toString() {
		return "[idTask:"+idTask+",kindSubmit:"+kindSubmit+"]";
	}
	
	public Long getIdTask() {
		return idTask;
	}
	public void setIdTask(Long idTask) {
		this.idTask = idTask;
	}

	public int getKindSubmit() {
		return kindSubmit;
	}

	public void setKindSubmit(int kindSubmit) {
		this.kindSubmit = kindSubmit;
	}

	
}
