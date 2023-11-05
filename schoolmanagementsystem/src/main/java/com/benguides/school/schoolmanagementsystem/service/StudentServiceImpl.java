package com.benguides.school.schoolmanagementsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.benguides.school.schoolmanagementsystem.model.Student;
import com.benguides.school.schoolmanagementsystem.repository.StudentRepository;


/*This class implements StudentService Interface
 * Handles student-related logic like creation, retrieval, updating and deletion
 * 
 * */
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> listAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudentById(Integer id) {
		studentRepository.deleteById(id);

	}

	@Override
	public Student getStudentById(Integer id) {
		Optional<Student> optional = studentRepository.findById(id);
		Student student = null;
		if (optional.isPresent()) {
			student = optional.get();
		} else {
			throw new RuntimeException("Student not found for id:: " + id);
		}
		return student;
	}
}
