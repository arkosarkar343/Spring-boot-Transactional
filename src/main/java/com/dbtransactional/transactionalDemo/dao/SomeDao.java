package com.dbtransactional.transactionalDemo.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.dbtransactional.transactionalDemo.domain.Student;

public class SomeDao {
	
	private EntityManager entityManager;

	@Transactional
	public boolean runTransaction(Student student) {
		entityManager.persist(student);
		entityManager.flush();
		return true;
	}
	
	public List<Student>  getStudent() {
		Query queryStudents = entityManager.createNativeQuery("select * from student");
		List<Student> students = (List<Student>)queryStudents.getResultStream().map(i-> new Student((String)i)).collect(Collectors.toList());
		return students;
		
	}

	public SomeDao setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		return this;
	}
	
	
}
