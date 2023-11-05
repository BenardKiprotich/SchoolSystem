package com.benguides.school.schoolmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.benguides.school.schoolmanagementsystem.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
