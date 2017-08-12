package com.in28minutes.soap.webservices.coursemanagement.soap.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.in28minutes.courses.Status;
import com.in28minutes.soap.webservices.coursemanagement.soap.bean.Course;

@Component
public class CourseDetailsService {

	private static List<Course> courses = new ArrayList<>();
	static {
		courses.add(new Course(1, "Spring", "10 steps"));
		courses.add(new Course(2, "Spring MVC", "10 steps"));
		courses.add(new Course(3, "Spring Boot", "6k students"));
		courses.add(new Course(4, "Design patterns", "In Java"));
	}

	public Course findById(int id) {
		for (Course course : courses) {
			if (course.getId() == id)
				return course;
		}
		return null;
	}

	public List<Course> findAll() {
		return courses;
	}

	public Status deleteById(int id) {

		Iterator<Course> courIterator = courses.iterator();
		while (courIterator.hasNext()) {
			Course c = courIterator.next();
			if (c.getId() == id) {
				courIterator.remove();
				return Status.SUCCESS;
			}
		}
		return Status.FAILURE;
	}

}
