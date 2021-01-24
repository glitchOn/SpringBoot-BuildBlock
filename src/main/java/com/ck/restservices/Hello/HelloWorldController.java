package com.ck.restservices.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloBean")
	public UserData helloWorldBean() {
		return new UserData("C", "K", "ck@hello.com");
	}
	 
}
