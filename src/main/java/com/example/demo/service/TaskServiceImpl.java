package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateTaskDto;
import com.example.demo.dto.UpdateTaskDto;
import com.example.demo.entity.Task;
import com.example.demo.entity.TaskGroup;
import com.example.demo.repository.TaskGroupRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

import client.example.demo.mapper.DtoMapper;

@Service
public class TaskServiceImpl implements TaskService 
{
	private final TaskRepository taskRepository;
	private final DtoMapper dtoMapper;
	private final UserRepository userRepository;
	private final TaskGroupRepository taskGroupRepository;
	
	public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository, 
			TaskGroupRepository taskGroupRepository, DtoMapper taskMapper)
	{
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
		this.taskGroupRepository = taskGroupRepository;
		this.dtoMapper = taskMapper;
	}
	
	public List<Task> getTasks(Long userId)
	{
		return taskRepository.getCurrentTasks(userId);
	}
	
	public List<Task> getDoneTasks(Long userId)
	{
		return taskRepository.getDoneTasks(userId);
	}
	
	@Override
	public Task createTask(CreateTaskDto createTaskDto, Long userId)
	{
		Task task = new Task();
		
		Optional<TaskGroup> taskGroupOptional = taskGroupRepository.findFirstByUserIdAndIsEndedOrderByCreatedAtDesc(userId);
		TaskGroup taskGroup = new TaskGroup();
		
		// If there is no recent TaskGroup or the most recent TaskGroup is not ended, create a new TaskGroup
        if (taskGroupOptional.isEmpty()) {
            taskGroup.setUser(userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found")));
            taskGroup.setCreatedDateTime(new Date());
            taskGroup.setCreatedBy(userId);
            taskGroup.setLastUpdateDateTime(new Date());
            taskGroup.setUpdatedBy(userId);
            taskGroup.setIsEnded(false);
            task.setTaskGroup(taskGroup);
            taskGroupRepository.save(taskGroup);
        }
        else {
        	
        	task.setTaskGroup(taskGroupOptional.get());
        }

        task.setDescription(createTaskDto.getDescription());
        task.setIsDone(createTaskDto.getIsDone());
        task.setIsEmailSent(createTaskDto.getIsEmailSent());
        task.setLastUpdateDateTime(new Date());
        task.setUser(userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found")));
        
        task = taskRepository.save(task);
        
		return task;
	}
	
	@Override
	public Task updateTask(UpdateTaskDto updateTaskDto, Long taskId, Long userId)
	{
		Task currentTask = taskRepository.findById(taskId).orElseThrow(RuntimeException::new);
		
		currentTask.setDescription(updateTaskDto.getDescription());
		currentTask.setIsDone(updateTaskDto.getIsDone());
		currentTask.setIsEmailSent(updateTaskDto.getIsEmailSent());
		currentTask.setLastUpdateDateTime(new Date());
		currentTask.setUpdatedBy(userId);
		currentTask.setCreatedBy(currentTask.getCreatedBy());
		currentTask.setCreatedDateTime(currentTask.getCreatedDateTime());
		currentTask.setUser(userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found")));
		
		return taskRepository.save(currentTask);
	}
	
	@Override
	public void deleteTask(Long taskId)
	{
		taskRepository.deleteById(taskId);
	}
}