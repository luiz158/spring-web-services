package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.helloworld.HelloWorldBean;

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

	// helloWorldBean/pathVariable/in28minutes
	@GetMapping(path = "/helloWorldBean/pathVariable/{name}")
	public HelloWorldBean helloWorldBeanWithPath(@PathVariable String name) {

		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
}
