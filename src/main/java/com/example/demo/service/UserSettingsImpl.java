package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UpdateUserSettingsDto;
import com.example.demo.entity.User;
import com.example.demo.entity.UserSettings;
import com.example.demo.repository.EmailRepository;
import com.example.demo.repository.UserSettingsRepository;

import client.example.demo.mapper.DtoMapper;

@Service
public class UserSettingsImpl implements UserSettingsService
{
	private final UserSettingsRepository userSettingsRepository;
	private final DtoMapper dtoMapper;
	private final UserService userService;
	private final EmailRepository emailRepository;	
	
	public UserSettingsImpl(UserSettingsRepository userSettingsRepository, DtoMapper dtoMapper,UserService userService,EmailRepository email)
	{
		this.emailRepository = email;
		this.userService = userService;
		this.userSettingsRepository = userSettingsRepository;
		this.dtoMapper = dtoMapper;
	}

	@Override
	public UserSettings getUserSettings(Long userId) {
		User user = userService.findById(userId);
		UserSettings userSettings = userSettingsRepository.getUserSettings(userId);
		if(null == userSettings) {
			userSettings = new UserSettings();
			
		}
		userSettings.setUser(user);
		userSettings.setEmail(emailRepository.findByUserSettings(userId));
		userSettingsRepository.save(userSettings);
		return userSettings;
	}
	@Transactional
	@Override
	public UserSettings updateUserSettings(UpdateUserSettingsDto userSettingsDto, Long userSettingsId, Long userId) {
		UserSettings currentUserSettings = userSettingsRepository.findById(userSettingsId).orElseThrow(RuntimeException::new);
		
		emailRepository.deleteByUserSettings(userSettingsId);
		
		userSettingsDto.getEmail().forEach(it -> {
			it.setUserSettings(userSettingsId);
			emailRepository.save(it);
		});
		
		currentUserSettings.setLongBreak(userSettingsDto.getLongBreak());
		currentUserSettings.setShortBreak(userSettingsDto.getShortBreak());
		currentUserSettings.setEmail(userSettingsDto.getEmail());
		currentUserSettings.setPomodoroTime(userSettingsDto.getPomodoroTime());
		
		return userSettingsRepository.save(currentUserSettings);
	}
	
	

}
