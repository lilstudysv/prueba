package com.liststudy.backendliststudy.taskrequest;

public class TaskRequestModel {

	private Long idTask;
	private Long idUserDeleteAccepted;

	public String toStringIdTask() {
		return "[idTask:"+idTask+"]";
	}
	
	public String toStringAll() {
		return "[idTask:"+idTask+",idUserDeleteAccepted:"+idUserDeleteAccepted+"]";
	}
	
	
	public Long getIdUserDeleteAccepted() {
		return idUserDeleteAccepted;
	}

	public void setIdUserDeleteAccepted(Long idUserDeleteAccepted) {
		this.idUserDeleteAccepted = idUserDeleteAccepted;
	}
	
	public Long getIdTask() {
		return idTask;
	}
	public void setIdTask(Long idTask) {
		this.idTask = idTask;
	}

	
}
