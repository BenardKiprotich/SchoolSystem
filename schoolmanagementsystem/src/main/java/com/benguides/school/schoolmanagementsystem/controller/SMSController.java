package com.benguides.school.schoolmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.benguides.school.schoolmanagementsystem.model.Course;
import com.benguides.school.schoolmanagementsystem.model.Student;
import com.benguides.school.schoolmanagementsystem.service.CourseService;
import com.benguides.school.schoolmanagementsystem.service.StudentService;


@Controller
public class SMSController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;

	@GetMapping("/")
	public ModelAndView homePage() {
		ModelAndView mav = new ModelAndView("index");

		List<Student> studentList = studentService.listAllStudents();
		mav.addObject("students", studentList);

		List<Course> courseList = courseService.listCourses();
		mav.addObject("courses", courseList);

		// back to return "index"
		return mav;
	}

	// Hander to create and save new student
	@PostMapping("/students/saveStudent")
	public ModelAndView addNewStudent(Student student) {
		ModelAndView mav = new ModelAndView("redirect:/");
		
		studentService.saveStudent(student);

		return mav;
	}

	@GetMapping("/students/updateStudentForm/{id}")
	public String updateStudentForm(@PathVariable(value = "id") Integer id, Model model) {
		Student student = studentService.getStudentById(id);

		model.addAttribute("student", student);

		return "update-student";
	}

	/*
	 * Handler method to direct to add new student form
	 * 
	 */
	@GetMapping("/students/addStudentForm")
	public String addStudentForm(Student student, Model model) {
		model.addAttribute("student", student);

		return "addnewstudent";
	}

	// Handler method to delete Student by id
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable(value = "id") Integer id) {
		studentService.deleteStudentById(id);
		return "redirect:/";

	}

	// Handler Methods for course
	@GetMapping("/course/addCourseForm")
	public String addCourseForm(Course course, Model model) {
		model.addAttribute("course", course);
		return "addnewcourse";
	}

	@PostMapping("/course/saveCourse")
	public String save(Course course) {
		courseService.saveCourse(course);
		
		return "redirect:/";
	}

	@GetMapping("/course/updateCourseForm/{id}")
	public String updateCourseForm(@PathVariable(value = "id") Integer id, Model model) {
		Course course = courseService.findCourseById(id);
		model.addAttribute("course", course);
		return "update-course";

	}

	@GetMapping("/course/deleteCourse/{id}")
	public String deleteCourse(@PathVariable(value = "id") Integer id) {
		courseService.deleteCourse(id);

		return "redirect:/";
	}

	/*
	 * The methods below are handler methods for student and course relationship
	 */
	@GetMapping("/addStudentCourse/{id}")
	public String getStudentById(@PathVariable("id") Integer id, Model model) {
		Student findStudentId = studentService.getStudentById(id);
		model.addAttribute("courses", courseService.listCourses());
		model.addAttribute("studentCourses", findStudentId);
		return "addStudentCourse";
	}

	// Method to save student course
	@GetMapping("/studentAddCourse/{id}/course")
	public String addStudentCourse(@PathVariable("id") Integer id, @RequestParam("courseId") Integer courseId,
			Model model) {
		Course course = courseService.findCourseById(courseId);
		Student student = studentService.getStudentById(id);

		if (student != null) {
			if(!student.hasCourse(course)) {
				student.getCourses().add(course);
			}
			studentService.saveStudent(student);
			model.addAttribute("student", student);
			model.addAttribute("courses", courseService.listCourses());

		}

		return "redirect:/";
	}
}
