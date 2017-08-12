package com.in28minutes.soap.webservices.coursemanagement.soap;

import javax.servlet.Servlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

	// Message dispatcher servlet

	// Application Context

	// URL to the web service - url --> /ws/*

	@Bean
	public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext context) {

		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);

		return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
	}

	@Bean(name = "courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema courseSchema) {

		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();

		// Porttype - CoursePort
		definition.setPortTypeName("CoursePort");

		// Namespace http://www.in28minutes.com/courses
		definition.setTargetNamespace("http://www.in28minutes.com/courses");

		// Location URI - ws
		definition.setLocationUri("/ws");

		// Schema
		definition.setSchema(courseSchema);

		return definition;
	}

	@Bean
	public XsdSchema coursesSchema() {

		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}

}
