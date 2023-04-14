package com.example.demo;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import client.example.demo.mapper.DtoMapper;

@Configuration
public class MapperConfig {
    @Bean
    DtoMapper taskMapper()
	{
		return Mappers.getMapper(DtoMapper.class);
	}
}