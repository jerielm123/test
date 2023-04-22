package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.SerializedName;

@Entity
@Table(name = "user")
public final class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	@OneToOne
	private UserSettings userSettings;
	
	public UserSettings getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(UserSettings userSettings) {
		this.userSettings = userSettings;
	}*/
	@SerializedName(value = "email")
	private String userEmail;
	
	@SerializedName(value = "family_name")
	private String lastName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@SerializedName(value = "given_name")
	private String firstName;
	
	private Date createdDateTime;
	
	private Long createdBy;
	
	private Date lastaUpdateDateTime;
	
	private Long updatedBy;
	
	/*
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}*/

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastaUpdateDateTime() {
		return lastaUpdateDateTime;
	}

	public void setLastaUpdateDateTime(Date lastaUpdateDateTime) {
		this.lastaUpdateDateTime = lastaUpdateDateTime;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
