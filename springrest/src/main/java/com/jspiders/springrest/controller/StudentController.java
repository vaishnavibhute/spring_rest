package com.jspiders.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrest.pojo.StudentPojo;
import com.jspiders.springrest.repository.StudentRepository;
import com.jspiders.springrest.response.StudentResponse;
import com.jspiders.springrest.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;
	

	@PostMapping(path = "/add")
	public ResponseEntity<StudentResponse> addstudent(@RequestBody StudentPojo pojo) {

		StudentPojo student = service.addStudent(pojo);
		StudentResponse response = new StudentResponse();

		// success flow
		if (student != null) {
			response.setMessage("Data Inserted Successfully. ");
			response.setData(student);

			return new ResponseEntity<StudentResponse>
											(response, HttpStatus.ACCEPTED);

		}
		// failure flow
		response.setMessage("Data not inserted. ");
		return new ResponseEntity<StudentResponse>
											(response, HttpStatus.BAD_REQUEST);

	}

	
//	@GetMapping(path = "/search", 
//			produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<StudentResponse> search(@RequestBody StudentPojo pojo) {
//		StudentPojo student = service.searchStudent(pojo.getId());
//		// success flow
//		if (student != null) {
//			return new ResponseEntity<StudentResponse>(new StudentResponse("Data found successfully", 
//																						student, null),
//																				HttpStatus.FOUND);
//		}
//		// failure flow
//		return new ResponseEntity<StudentResponse>(new StudentResponse("Data does not exist", 
//																				        null, null),
//														HttpStatus.NOT_FOUND);
//	}
	
	
	//by changing path & using RequestParam--->
	
	@GetMapping(path = "/search{id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse> search(@RequestParam int id) {
		StudentPojo student = service.searchStudent(id);
		// success flow
		if (student != null) {
			return new ResponseEntity<StudentResponse>(new StudentResponse("Data found successfully", 
																						student, null),
																				HttpStatus.FOUND);
		}
		// failure flow
		return new ResponseEntity<StudentResponse>(new StudentResponse("Data does not exist", 
																				        null, null),
														HttpStatus.NOT_FOUND);
	}
	
		
	
	@GetMapping(path = "/searchAll",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponse>searchAllStudents(){
		List<StudentPojo>students=service.searchAllStudents();
		if(students!=null) {
			return new ResponseEntity<StudentResponse>(new StudentResponse("Data found successfully",
																				null,students), 
																		HttpStatus.FOUND);
		}
		return new ResponseEntity<StudentResponse>(new StudentResponse("No student data found. ",
																					null,null), 
																	HttpStatus.NOT_FOUND);
	}
}
