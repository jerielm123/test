package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>
{
	@Query("SELECT t FROM Task t LEFT JOIN TaskGroup tg ON t.taskGroup.id = tg.id"
			+ " WHERE t.user.id = ?1 AND tg.isEnded = false ORDER BY tg.lastUpdateDateTime DESC")
	List<Task> getCurrentTasks(Long userId);
	
	@Query("SELECT t FROM Task t LEFT JOIN TaskGroup tg ON t.taskGroup.id = tg.id"
			+ " WHERE t.user.id = ?1  AND t.isDone = true AND t.isEmailSent = false"
			+ " ORDER BY tg.lastUpdateDateTime DESC")
	List<Task> getDoneTasks(Long userId);
	
	@Modifying
    @Query("UPDATE Task t SET t.isEmailSent = :isEmailSent, t.lastUpdateDateTime = :currentDate, t.updatedBy = :userId WHERE t.id = :id")
    void updateTaskById(Long id, Boolean isEmailSent, LocalDate currentDate, Long userId);
}