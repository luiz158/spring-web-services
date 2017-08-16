package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Define class as controller:
@RestController
public class HelloWorldController {
	// Define method: GET
	// Define URI: /helloWorld
	// method: helloWorld

	@GetMapping(path = "/helloWorld")
	public String helloWorld() {

		return "Hello World";
	}

	@GetMapping(path = "/helloWorldBean")
	public HelloWorldBean helloWorldBean() {

		return new HelloWorldBean("Hello World");
	}
}
