package com.codewithakshay.cyperts.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithakshay.cyperts.entity.User;
import com.codewithakshay.cyperts.repository.UserRepository;
import com.codewithakshay.cyperts.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		return allUsers;
	}

	@Override
	public User editUser(Long id, User user) {
		user.setId(id);
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public boolean deleteUser(Long id) {
		userRepository.deleteById(id);
		return true;
	}

}
