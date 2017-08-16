package com.in28minutes.soap.webservices.coursemanagement.soap;

import java.util.Collections;
import java.util.List;
import javax.servlet.Servlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

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

	@Bean
	public XwsSecurityInterceptor securityInterceptor() {
		XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
		securityInterceptor.setCallbackHandler(callbackHandler());
		securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
		// CallBack handler --> SimplePasswordValidationCallBackHandler
		// Security Policy --> securityPolicy.xml

		return securityInterceptor;
	}

	@Bean
	public SimplePasswordValidationCallbackHandler callbackHandler() {

		SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
		handler.setUsersMap(Collections.singletonMap("user", "password"));
		return handler;
	}

	// XwsSecurityInterceptor
	// Interceptors.add --> XwsSecurityInterceptor
	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		interceptors.add(securityInterceptor());
	}

}
