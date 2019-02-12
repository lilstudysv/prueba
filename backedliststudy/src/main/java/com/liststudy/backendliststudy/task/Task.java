package com.liststudy.backendliststudy.task;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.liststudy.backendliststudy.user.User;

@Entity
@Table(name="task")
public class Task {
	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private KindStateTask state;
	@ManyToOne
	private KindTopicTask topic;
	private String title;
	private String description;
	private Double price;
	@ManyToOne
	private User creator;
	@ManyToOne
	private User resolver;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public KindStateTask getState() {
		return state;
	}
	public void setState(KindStateTask state) {
		this.state = state;
	}
	public KindTopicTask getTopic() {
		return topic;
	}
	public void setTopic(KindTopicTask topic) {
		this.topic = topic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public User getResolver() {
		return resolver;
	}
	public void setResolver(User resolver) {
		this.resolver = resolver;
	}
	
}
