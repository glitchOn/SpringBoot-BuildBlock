package com.ck.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.restservices.entities.User;
import com.ck.restservices.exceptions.UserExistsException;
import com.ck.restservices.exceptions.UserNotFoundException;
import com.ck.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public List<User> getAllusers() {
		return userRepo.findAll();	
	}

	public User createUser(User user) throws UserExistsException {
		if(userRepo.findByUserName(user.getUserName()) != null)
				throw new UserExistsException("User already exists");
		return userRepo.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> optionalUser = userRepo.findById(id);
		
		if(!optionalUser.isPresent())
			throw new UserNotFoundException("User Not Found");
		
		return optionalUser;
	}

	public User updateUserById(Long id,User user) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepo.findById(id);
		
		if(!optionalUser.isPresent())
			throw new UserNotFoundException("User Not Found, please validate");
		
		user.setId(id);
		return userRepo.save(user);
	}

	public void deleteUserById(Long id) throws UserNotFoundException {

		Optional<User> optionalUser = userRepo.findById(id);
		if(!optionalUser.isPresent())
			throw new UserNotFoundException("User Not Found, please validate");
		
		userRepo.deleteById(id);
	}

	public User findByUserName(String userName) {
		return	userRepo.findByUserName(userName);
	}
}
