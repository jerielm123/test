package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	UserRepository userRepository;
		
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User saveOrRetrieveUser(User user) {
		User retrieveUser = userRepository.findByUserEmail(user.getUserEmail());
		if(null != retrieveUser) {
			return retrieveUser;
		}
		user.setCreatedDateTime(new Date());
		user.setLastaUpdateDateTime(new Date());
		return userRepository.save(user);
	}
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

}
