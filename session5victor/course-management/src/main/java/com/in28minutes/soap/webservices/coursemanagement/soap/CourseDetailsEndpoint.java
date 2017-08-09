package com.in28minutes.soap.webservices.coursemanagement.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;

@Endpoint
public class CourseDetailsEndpoint {

	@PayloadRoot(namespace = "http://www.in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(request.getId());
		courseDetails.setName("Microservices Course");
		courseDetails.setDescription("That would be a wonderful course");
		
		response.setCourseDetails(courseDetails);
		return response;
	}

}
