package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

}
