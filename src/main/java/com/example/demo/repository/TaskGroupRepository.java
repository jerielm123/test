package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.TaskGroup;

import java.time.LocalDate;
import java.util.Optional;

public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long>
{

	@Query("SELECT tg FROM TaskGroup tg WHERE tg.user.id = ?1 AND tg.isEnded = false ORDER BY tg.lastUpdateDateTime DESC")
	Optional<TaskGroup> findFirstByUserIdAndIsEndedOrderByCreatedAtDesc(Long userId);
	
	@Modifying
    @Query("UPDATE TaskGroup tg SET tg.isEnded = :isEnded, tg.lastUpdateDateTime = :currentDate, tg.updatedBy = :userId WHERE tg.id = :id")
    void updateTaskGroupById(Long id, Boolean isEnded, LocalDate currentDate, Long userId);
}