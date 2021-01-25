package com.ck.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ck.restservices.entities.User;
import com.ck.restservices.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("/users")
	public List<User> getAllUsers(){
		return	service.getAllusers();
	}
	
	@PostMapping("/users")
	public User createUser( @RequestBody User user ){
		return	service.createUser(user);
	}

	@GetMapping("users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		return	service.getUserById(id);
	}
	
	@PutMapping("users/{id}")
	public User updateUserById(@PathVariable("id") Long id ,@RequestBody User user) {
		return service.updateUserById(id, user);
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		 service.deleteUserById(id);
	}
	
	@GetMapping("users/byusername/{name}")
	public User getUserById(@PathVariable("name") String userName) {
		return	service.findByUserName(userName);
	}
}
