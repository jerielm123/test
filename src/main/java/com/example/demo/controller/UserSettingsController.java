package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UpdateUserSettingsDto;
import com.example.demo.entity.UserSettings;
import com.example.demo.service.UserSettingsService;

@RestController
@RequestMapping("/user-settings")
public class UserSettingsController 
{
	@Autowired
	private UserSettingsService userSettingsService;
	
	public UserSettingsController(UserSettingsService userSettingsService)
	{
		this.userSettingsService = userSettingsService;
	}
	
	@GetMapping("/{userId}")
	public UserSettings getTasks(@PathVariable Long userId) {
		return userSettingsService.getUserSettings(userId);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<UserSettings> updateTask(@PathVariable Long id, @RequestBody UpdateUserSettingsDto userSettingsDto) {
		UserSettings currentUserSettings = userSettingsService.updateUserSettings(userSettingsDto, id, userSettingsDto.getUserId());
        return ResponseEntity.ok(currentUserSettings);
    }
	

}
