package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "userSettings")
public class UserSettings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Transient
	private List<Email> email;
	
	public List<Email> getEmail() {
		return email;
	}
	
	public void setEmail(List<Email> email) {
		this.email = email;
	}

	private Integer shortBreak;

	private Integer longBreak;

	public Integer getShortBreak() {
		return shortBreak;
	}

	public void setShortBreak(Integer shortBreak) {
		this.shortBreak = shortBreak;
	}

	public Integer getLongBreak() {
		return longBreak;
	}

	public void setLongBreak(Integer longBreak) {
		this.longBreak = longBreak;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
