package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>
{
	@Query("SELECT t FROM Task t LEFT JOIN TaskGroup tg ON t.taskGroup.id = tg.id"
			+ " WHERE t.user.id = ?1 AND tg.isEnded = false ORDER BY tg.lastUpdateDateTime DESC")
	List<Task> getCurrentTasks(Long userId);
}