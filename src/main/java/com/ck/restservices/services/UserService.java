package com.ck.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.restservices.entities.User;
import com.ck.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public List<User> getAllusers() {
		return userRepo.findAll();	
	}

	public User createUser(User user) {
		return userRepo.save(user);
	}

	public Optional<User> getUserById(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		return optionalUser;
	}

	public User updateUserById(Long id,User user) {
		user.setId(id);
		return userRepo.save(user);
	}

	public void deleteUserById(Long id) {
		if(userRepo.findById(id).isPresent())
			userRepo.deleteById(id);
	}

	public User findByUserName(String userName) {
		return	userRepo.findByUserName(userName);
	}
}
