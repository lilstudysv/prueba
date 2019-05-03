package com.liststudy.backendliststudy.taskrequest;

public class UserRequestModel {

	private Long id;
	private String name;
	private Long averageGrade;
	private Integer totalTaskResolved;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(Long averageGrade) {
		this.averageGrade = averageGrade;
	}
	public Integer getTotalTaskResolved() {
		return totalTaskResolved;
	}
	public void setTotalTaskResolved(Integer totalTaskResolved) {
		this.totalTaskResolved = totalTaskResolved;
	}
	
}
