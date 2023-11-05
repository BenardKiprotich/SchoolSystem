package com.benguides.school.schoolmanagementsystem.service;

import java.util.List;

import com.benguides.school.schoolmanagementsystem.model.Student;



public interface StudentService {
	//Gets the list of all students
	List<Student> listAllStudents();
	
	//Save the student in the database;
	Student saveStudent(Student student);
	
	//method to delete the student
	void deleteStudentById(Integer id);
	
	Student getStudentById(Integer id);
}
