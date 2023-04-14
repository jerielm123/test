package client.example.demo.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.entity.Task;
import com.example.demo.dto.CreateTaskDto;
import com.example.demo.dto.UpdateTaskDto;

@Mapper(componentModel = "spring")
@ComponentScan
public interface TaskMapper {
	CreateTaskDto toTaskDto(Task task);
	Task toCreateTask(CreateTaskDto task);
	
	Task toUpdateTask(UpdateTaskDto task);
}