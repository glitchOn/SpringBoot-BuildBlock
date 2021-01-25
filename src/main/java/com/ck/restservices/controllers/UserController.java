package com.ck.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.ck.restservices.entities.User;
import com.ck.restservices.exceptions.UserExistsException;
import com.ck.restservices.exceptions.UserNotFoundException;
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
	public ResponseEntity<Void> createUser( @RequestBody User user, UriComponentsBuilder builder ){
		try {
			service.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		try {
			return	service.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PutMapping("users/{id}")
	public User updateUserById(@PathVariable("id") Long id ,@RequestBody User user) {
		try {
			return service.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@DeleteMapping("users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		 try {
			service.deleteUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}
	}
	
	@GetMapping("users/byusername/{name}")
	public User getUserById(@PathVariable("name") String userName) {
		return	service.findByUserName(userName);
	}
}
