package client.example.demo.mapper;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CreateTaskDto;
import com.example.demo.dto.UpdateTaskDto;
import com.example.demo.dto.UpdateUserSettingsDto;
import com.example.demo.entity.Task;
import com.example.demo.entity.UserSettings;
@Service
public class DtoMapperImpl implements DtoMapper {

	@Override
	public CreateTaskDto toTaskDto(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task toCreateTask(CreateTaskDto task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task toUpdateTask(UpdateTaskDto task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserSettings toUpdateUserSettings(UpdateUserSettingsDto updateUserSettingsDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
