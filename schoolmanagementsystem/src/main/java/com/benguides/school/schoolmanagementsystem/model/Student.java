package com.benguides.school.schoolmanagementsystem.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Integer id;

	@Column(name = "firstname", nullable = false)
	private String fName;

	@Column(name = "middlename", nullable = true)
	private String mName;

	@Column(name = "lastname", nullable = false)
	private String lName;
	
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "phone", nullable = false)
	private Long phone;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dob", nullable = false)
	private Date dob;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "student_id")}, inverseJoinColumns = {
			@JoinColumn(name = "course_id", referencedColumnName = "course_id") })
	@JsonIgnore
	private Set<Course> courses = new HashSet<>();

	public boolean hasCourse(Course course) {
		for (Course studentCourse: getCourses()) {
			if(studentCourse.getCourseId() == course.getCourseId()) {
				return true;
			}
		}
		return false;
	}

}
