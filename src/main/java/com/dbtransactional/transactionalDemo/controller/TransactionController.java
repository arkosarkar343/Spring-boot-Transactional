package com.dbtransactional.transactionalDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbtransactional.transactionalDemo.dao.SomeDao;
import com.dbtransactional.transactionalDemo.domain.Student;

@RestController
public class TransactionController {

	@Autowired
	private SomeDao someDao;
	
	@GetMapping("/check-transaction")
	public boolean checkTransaction(@RequestParam("name") String name) {
		return someDao.runTransaction(new Student(name));
		
	}
	@GetMapping("/students")
	public List<Student> getStudents() {
		return someDao.getStudent();
	}
}
