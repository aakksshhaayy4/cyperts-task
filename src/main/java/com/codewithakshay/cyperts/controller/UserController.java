package com.codewithakshay.cyperts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithakshay.cyperts.entity.User;
import com.codewithakshay.cyperts.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		try {
			User userSavedData = userService.createUser(user);
			if (userSavedData != null)
				return new ResponseEntity<>(userSavedData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllUsers() {
		try {
			List<User> usersList = userService.getAllUsers();
			if (!usersList.isEmpty())
				return new ResponseEntity<>(usersList, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> editUser(@PathVariable Long id, @RequestBody User user) {
		try {
			User userSavedData = userService.editUser(id, user);
			if (userSavedData != null)
				return new ResponseEntity<>(userSavedData, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		try {
			boolean isUserDeleted = userService.deleteUser(id);
			if (isUserDeleted == true)
				return new ResponseEntity<>("User Deleted Successfully ", HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception while deleting user : " + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
