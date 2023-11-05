package com.benguides.school.schoolmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benguides.school.schoolmanagementsystem.model.Course;
import com.benguides.school.schoolmanagementsystem.repository.CourseRepository;



@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<Course> listCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public Course findCourseById(Integer courseId) {
		Optional<Course> optional = courseRepository.findById(courseId);
		Course course = null;
		if (optional.isPresent()) {
			course = optional.get();
		} else {
			throw new RuntimeException("Course not found for id:: " + courseId);
		}
		return course;
	}

	@Override
	public void deleteCourse(Integer course_id) {
		courseRepository.deleteById(course_id);
	}
}
