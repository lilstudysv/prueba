package com.liststudy.backendliststudy.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.liststudy.backendliststudy.user.User;
import javax.persistence.JoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="task")
@Data
@EqualsAndHashCode
public class Task implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	@Version
	private Long version;
	@Enumerated
	@Column(columnDefinition = "smallint")
	private EnumStateTask state;
	@Enumerated
	@Column(columnDefinition = "smallint")
	private EnumTopicTask topic;
	@Column(columnDefinition = "smallint")
	private EnumKindTask kind;
	private String title;
	private String description;
	private Double price;
	@ManyToOne
	private User creator;
	@ManyToOne
	private User resolver;
	@ManyToMany
	@JoinTable(
	  name = "task_request_users", 
	  joinColumns = { @JoinColumn(name = "task_id") }, 
	  inverseJoinColumns = { @JoinColumn(name = "user_id") }
		)
	private List<User> requestUsers;
	@ManyToMany
	@JoinTable(
		name = "task_accepted_users", 
		joinColumns = { @JoinColumn(name = "task_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "user_id") }
	   )
	private List<User> acceptedUsers;
	

}
