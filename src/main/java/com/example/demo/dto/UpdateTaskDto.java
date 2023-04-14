package com.example.demo.dto;

import java.util.Date;

public class UpdateTaskDto {
	private Long userId;

	private String description;
	
	private Boolean isDone;
	
	private Date lastUpdateDateTime;

	private Long updatedBy;
	
	private Boolean isEmailSent;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public Date getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(Date lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsEmailSent() {
		return isEmailSent;
	}

	public void setIsEmailSent(Boolean isEmailSent) {
		this.isEmailSent = isEmailSent;
	}
}
