package com.example.demo.dto;

import java.util.Date;

public class CreateTaskDto {
	private Long userId;
	
	private Long taskGroupId;

	private String description;
	
	private Boolean isDone;
	
	private Date createdDateTime;
	
	private Long createdBy;
	
	private Date lastUpdateDateTime;

	private Long updatedBy;
	
	private Boolean isEmailSent;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long client_id) {
		this.userId = client_id;
	}

	public Long getTaskGroupId() {
		return taskGroupId;
	}

	public void setTaskGroupId(Long taskGroupId) {
		this.taskGroupId = taskGroupId;
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
