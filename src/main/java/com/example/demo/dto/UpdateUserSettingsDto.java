package com.example.demo.dto;

public class UpdateUserSettingsDto {
	private Long id;
	
	private Long userId;
	
	private Integer shortBreak;
	
	private Integer longBreak;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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
