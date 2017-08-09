package com.in28minutes.soap.webservices.coursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

@EnableWs
@Configuration
public class WebServiceConfig {

	// Message dispatcher servlet

	// Application Context

	// URL to the web service - url --> /ws/*

	@Bean
	ServletRegistrationBean<?> messageDispatcherServlet(ApplicationContext context) {

		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		
		return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
	}

}
