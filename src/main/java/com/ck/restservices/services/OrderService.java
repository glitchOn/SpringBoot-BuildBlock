package com.ck.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ck.restservices.entities.Order;
import com.ck.restservices.entities.User;
import com.ck.restservices.exceptions.UserNotFoundException;
import com.ck.restservices.repositories.OrderRepository;
import com.ck.restservices.repositories.UserRepository;

@Service
public class OrderService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	
	public List<Order> getAllOrder(Long userId) throws UserNotFoundException{
		 Optional<User> userOptional = userRepo.findById(userId);
		 if(!userOptional.isPresent())
			 throw new UserNotFoundException("User is not found");
		 
		 return userOptional.get().getOrder();
	} 
	
	public Order createOrder(Long userId, Order order) throws UserNotFoundException{
		 Optional<User> userOptional = userRepo.findById(userId);
		 if(!userOptional.isPresent())
			 throw new UserNotFoundException("User is not found");
		 
		 order.setUser(userOptional.get());
		 return orderRepo.save(order);
	}
}
