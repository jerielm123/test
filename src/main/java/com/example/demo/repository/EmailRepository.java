package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Email;


public interface EmailRepository extends JpaRepository<Email, Long>  {
	
	public List<Email> findByUserSettings(Long userSettings);
	
	@Modifying
	@Query("DELETE FROM Email WHERE userSettings =:userSettingsid")	
	public void deleteByUserSettings(@Param("userSettingsid") Long userSettings);

}
