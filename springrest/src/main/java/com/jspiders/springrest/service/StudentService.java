package com.jspiders.springrest.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspiders.springrest.pojo.StudentPojo;
import com.jspiders.springrest.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;
	
	public StudentPojo addStudent(StudentPojo pojo) {
		StudentPojo student = repository.addStudent(pojo);
		return student;
	}
	
	public StudentPojo searchStudent(int id) {
		StudentPojo student = repository.searchStudent(id);
		return student;
	}
	
	public List<StudentPojo> searchAllStudents(){
		List<StudentPojo> students=repository.searchAllStudents();
		return students;
	}
}
