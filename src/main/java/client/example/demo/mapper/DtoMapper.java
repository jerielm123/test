package client.example.demo.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.entity.Task;
import com.example.demo.entity.UserSettings;
import com.example.demo.dto.CreateTaskDto;
import com.example.demo.dto.UpdateTaskDto;
import com.example.demo.dto.UpdateUserSettingsDto;

@Mapper(componentModel = "spring")
@ComponentScan
public interface DtoMapper {
	CreateTaskDto toTaskDto(Task task);
	Task toCreateTask(CreateTaskDto task);
	Task toUpdateTask(UpdateTaskDto task);
	
	UserSettings toUpdateUserSettings(UpdateUserSettingsDto updateUserSettingsDto);
}