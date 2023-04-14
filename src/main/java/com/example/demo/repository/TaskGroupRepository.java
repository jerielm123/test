package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TaskGroup;
import java.util.Optional;

public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long>
{

	@Query("SELECT tg FROM TaskGroup tg WHERE tg.user.id = ?1 AND tg.isEnded = false ORDER BY tg.lastUpdateDateTime DESC")
	Optional<TaskGroup> findFirstByUserIdAndIsEndedOrderByCreatedAtDesc(Long userId);
}