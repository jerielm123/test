package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UpdateUserSettingsDto;
import com.example.demo.entity.UserSettings;
import com.example.demo.repository.UserSettingsRepository;

import client.example.demo.mapper.DtoMapper;

@Service
public class UserSettingsImpl implements UserSettingsService
{
	private final UserSettingsRepository userSettingsRepository;
	private final DtoMapper dtoMapper;
	
	public UserSettingsImpl(UserSettingsRepository userSettingsRepository, DtoMapper dtoMapper)
	{
		this.userSettingsRepository = userSettingsRepository;
		this.dtoMapper = dtoMapper;
	}

	@Override
	public UserSettings getUserSettings(Long userId) {
		return userSettingsRepository.getUserSettings(userId);
	}

	@Override
	public UserSettings updateUserSettings(UpdateUserSettingsDto userSettingsDto, Long userSettingsId, Long userId) {
		UserSettings userSettings = dtoMapper.toUpdateUserSettings(userSettingsDto);
		UserSettings currentUserSettings = userSettingsRepository.findById(userSettingsId).orElseThrow(RuntimeException::new);
		
		currentUserSettings.setLongBreak(userSettings.getLongBreak());
		currentUserSettings.setShortBreak(userSettings.getShortBreak());
		currentUserSettings.setEmail(userSettings.getEmail());
		
		return userSettingsRepository.save(currentUserSettings);
	}

}
