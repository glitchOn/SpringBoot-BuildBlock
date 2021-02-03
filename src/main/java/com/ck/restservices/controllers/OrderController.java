package com.ck.restservices.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.ck.restservices.entities.Order;
import com.ck.restservices.exceptions.UserNotFoundException;
import com.ck.restservices.services.OrderService;

@RestController
@RequestMapping("/users")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/{id}/orders")
	public List<Order> getAllOrders(@PathVariable("id") Long userId){
		try {
			return orderService.getAllOrder(userId);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PostMapping("/{id}/orders")
	public ResponseEntity<Void> createOrder(@PathVariable("id") Long userId, @RequestBody Order order, UriComponentsBuilder builder ){
		try {
			orderService.createOrder(userId,order);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/users/{id}/orders").buildAndExpand(userId).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
