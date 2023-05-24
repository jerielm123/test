package com.example.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateTaskDto;
import com.example.demo.dto.UpdateTaskDto;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController 
{
	
	@Autowired
	private TaskService taskService;
	
	public TaskController(TaskService taskService)
	{
		this.taskService = taskService;
	}
	
	@GetMapping("/tasks/{userId}")
	public List<Task> getTasks(@PathVariable Long userId) {
		return taskService.getTasks(userId);
	}

	@PostMapping
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskDto taskDto) throws URISyntaxException {
		Task task = taskService.createTask(taskDto, taskDto.getUserId());
        return ResponseEntity.created(new URI("/task/" + taskDto.getUserId())).body(task);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody UpdateTaskDto taskDto) {
        Task currentTask = taskService.updateTask(taskDto, id, taskDto.getUserId());
        return ResponseEntity.ok(currentTask);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}