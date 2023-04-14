package com.example.demo.service;

import com.example.demo.dto.UpdateUserSettingsDto;
import com.example.demo.entity.UserSettings;

public interface UserSettingsService 
{
	UserSettings getUserSettings(Long userId);
	
	UserSettings updateUserSettings(UpdateUserSettingsDto userSettingsDto, Long userSettingsId, Long userId);
}