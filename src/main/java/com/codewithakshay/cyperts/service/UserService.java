package com.codewithakshay.cyperts.service;

import java.util.List;

import com.codewithakshay.cyperts.entity.User;

public interface UserService {

	public User createUser(User user);

	public List<User> getAllUsers();

	public User editUser(Long id, User user);

	public boolean deleteUser(Long id);

}
