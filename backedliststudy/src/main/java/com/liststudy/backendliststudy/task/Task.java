package com.liststudy.backendliststudy.task;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.liststudy.backendliststudy.user.User;

/**/

@Entity
@Table(name="task")
public class Task {
	
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

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EnumStateTask getState() {
		return state;
	}
	public void setState(EnumStateTask state) {
		this.state = state;
	}
	public EnumTopicTask getTopic() {
		return topic;
	}
	public void setTopic(EnumTopicTask topic) {
		this.topic = topic;
	}
	public EnumKindTask getKind() {
		return kind;
	}
	public void setKind(EnumKindTask kind) {
		this.kind = kind;
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
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
}
