package com.example.demo;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import client.example.demo.mapper.TaskMapper;

@Configuration
public class MapperConfig {
    @Bean
    TaskMapper taskMapper()
	{
		return Mappers.getMapper(TaskMapper.class);
	}
}