package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.UserSettings;

public interface UserSettingsRepository extends JpaRepository<UserSettings, Long> 
{
	@Query("SELECT us FROM UserSettings us WHERE us.user.id = ?1")
	UserSettings getUserSettings(Long userId);
}