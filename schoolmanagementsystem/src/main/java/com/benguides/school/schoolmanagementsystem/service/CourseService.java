package com.benguides.school.schoolmanagementsystem.service;

import java.util.List;

import com.benguides.school.schoolmanagementsystem.model.Course;

public interface CourseService {
	List<Course> listCourses();

	Course saveCourse(Course course);

	void deleteCourse(Integer courseId);

	Course findCourseById(Integer courseId);
}
