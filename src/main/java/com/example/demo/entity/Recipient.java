package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipient")

public final class Recipient {
	@Id
	@GeneratedValue(generator = "client_seq")
	private Long recipientId;
	
	private Long userId;
	
	private String email;
	
	private Integer emailTypeId;
	
	private Date createdDateTime;
	
	private Long createdBy;
	
	private Date lastaUpdateDateTime;
	
	private Long updatedBy;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmailTypeId() {
		return emailTypeId;
	}

	public void setEmailTypeId(Integer emailTypeId) {
		this.emailTypeId = emailTypeId;
	}

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
}
