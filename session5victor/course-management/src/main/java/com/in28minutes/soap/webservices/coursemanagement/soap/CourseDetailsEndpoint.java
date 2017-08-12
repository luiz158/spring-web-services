package com.in28minutes.soap.webservices.coursemanagement.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.in28minutes.courses.CourseDetails;
import com.in28minutes.courses.DeleteCourseRequest;
import com.in28minutes.courses.DeleteCourseResponse;
import com.in28minutes.courses.GetAllCourseDetailsRequest;
import com.in28minutes.courses.GetAllCourseDetailsResponse;
import com.in28minutes.courses.GetCourseDetailsRequest;
import com.in28minutes.courses.GetCourseDetailsResponse;
import com.in28minutes.courses.Status;
import com.in28minutes.soap.webservices.coursemanagement.soap.bean.Course;
import com.in28minutes.soap.webservices.coursemanagement.soap.service.CourseDetailsService;

@Endpoint
public class CourseDetailsEndpoint {

	@Autowired
	private CourseDetailsService courseService;

	@PayloadRoot(namespace = "http://www.in28minutes.com/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

		Course course = courseService.findById(request.getId());

		GetCourseDetailsResponse response = mapCourseDetails(course);

		return response;
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();

		CourseDetails courseDetails = mapCourse(course);

		response.setCourseDetails(courseDetails);

		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();

		for (Course course : courses) {
			CourseDetails courseDetails = mapCourse(course);
			response.getCourseDetails().add(courseDetails);
		}

		return response;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

	@PayloadRoot(namespace = "http://www.in28minutes.com/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
			@RequestPayload GetAllCourseDetailsRequest request) {

		List<Course> courses = courseService.findAll();

		return mapAllCourseDetails(courses);
	}

	@PayloadRoot(namespace = "http://www.in28minutes.com/courses", localPart = "DeleteCourseRequest")
	@ResponsePayload
	public DeleteCourseResponse processDeleteCourseRequest(@RequestPayload DeleteCourseRequest request) {

		Status status = courseService.deleteById(request.getId());

		DeleteCourseResponse response = new DeleteCourseResponse();

		response.setStatus(mapStatus(status));

		return response;

	}

	private com.in28minutes.courses.Status mapStatus(Status status) {
		// TODO Auto-generated method stub
		return null;
	}

}
