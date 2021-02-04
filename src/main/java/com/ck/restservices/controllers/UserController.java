package com.ck.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.ck.restservices.entities.User;
import com.ck.restservices.exceptions.UserExistsException;
import com.ck.restservices.exceptions.UserNameNotFoundException;
import com.ck.restservices.exceptions.UserNotFoundException;
import com.ck.restservices.services.UserService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping()
	public List<User> getAllUsers(){
		return	service.getAllusers();
	}

	@PostMapping()
	public ResponseEntity<Void> createUser( @Valid @RequestBody User user, UriComponentsBuilder builder ){
		try {
			service.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id) {
		try {
			return	service.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public User updateUserById(@Min(1) @PathVariable("id") Long id ,@RequestBody User user) {
		try {
			 User updateUser= service.updateUserById(id, user);
			 updateUser.add(linkTo(methodOn(UserController.class).updateUserById(user.getId(),user)).withSelfRel());
			// updateUser.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withRel("/users"));
			 updateUser.add(linkTo(methodOn(UserController.class).getUserByName(user.getUserName())).withRel("/users"));
			return updateUser; 
		} catch (UserNotFoundException | UserNameNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		try {
			service.deleteUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

		}
	}

	@GetMapping("byusername/{name}")
	public User getUserByName(@PathVariable("name") String userName) throws UserNameNotFoundException {
		User user =	service.findByUserName(userName);
	
		if(user ==null)
			throw new UserNameNotFoundException("User with " +userName + "is not found");
		
		user.add(linkTo(methodOn(UserController.class).getUserByName(user.getUserName())).withSelfRel());
		user.add(linkTo(methodOn(UserController.class).updateUserById(user.getId(),user)).withRel("updateUserById"));
		user.add(linkTo(methodOn(UserController.class).getAllUsers()).withRel("Get All Users"));
		return user;
	}
}
