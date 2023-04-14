package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CreateTaskDto;
import com.example.demo.dto.UpdateTaskDto;
import com.example.demo.entity.Task;

public interface TaskService 
{
	List<Task> getTasks(Long userId);
	
	Task createTask(CreateTaskDto createTaskDto, Long userId);
	
	Task updateTask(UpdateTaskDto updateTaskDto, Long taskId, Long userId);
	
	void deleteTask(Long taskId);
}