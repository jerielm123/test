package com.example.demo.controller;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/user")
public class UserController {
	UserService userService;
	Gson gson;

	UserController(UserService userService, Gson gson) {
		this.userService = userService;
		this.gson = gson;
	}

	@GetMapping
	public User login(@RequestHeader(required = false) Map<String, String> headers) {
		if (headers.get("authorization") != null) {
			String authToken = headers.get("authorization").replace("Bearer", "").trim();
			String[] chunks = authToken.split("\\.");
			Base64.Decoder decoder = Base64.getUrlDecoder();
			String payload = new String(decoder.decode(chunks[1]));
			User user = gson.fromJson(payload, User.class);
			return userService.saveOrRetrieveUser(user);		

		}
		return null;
	}
	
	@GetMapping("/all")
	public List<User> gelAllUser() {
		return userService.getAllUser();
	}
	
	
}
