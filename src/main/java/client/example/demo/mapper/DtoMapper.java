package client.example.demo.mapper;

import com.example.demo.dto.CreateTaskDto;
import com.example.demo.dto.UpdateTaskDto;
import com.example.demo.dto.UpdateUserSettingsDto;
import com.example.demo.entity.Task;
import com.example.demo.entity.UserSettings;

public interface DtoMapper {
	CreateTaskDto toTaskDto(Task task);
	Task toCreateTask(CreateTaskDto task);
	Task toUpdateTask(UpdateTaskDto task);
	
	UserSettings toUpdateUserSettings(UpdateUserSettingsDto updateUserSettingsDto);
}